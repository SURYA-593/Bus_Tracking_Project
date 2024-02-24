package com.demo.bustracking.presentation

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.SwipeableState
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.swipeable
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.demo.bustracking.Constant
import com.demo.bustracking.ErrorMessage
import com.demo.bustracking.Loading
import com.demo.bustracking.Location
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState


@Composable
fun ScreenView(
    viewModel : MainViewModel = hiltViewModel()
){
}

@Composable
fun MainScreen(
    viewModel : MainViewModel = hiltViewModel(),
    openGMapWithQuery : (query : String) -> Unit,
    openContactDialog : ()-> Unit,
    toast : (String) -> Unit
){

    val state = viewModel.state.collectAsState()
    if (state.value.isLoading){
        Loading()
    }
    else if (state.value.errorMessage != null){
        toast(state.value.errorMessage ?: "Error [[[[]]]]")
    }
    var locationString by remember {
        mutableStateOf("")
    }

    var isSwiped by remember {
        mutableStateOf(false)
    }
    val swipeRefreshStaate = rememberSwipeRefreshState(isRefreshing = isSwiped)
    SwipeRefresh(
        state = swipeRefreshStaate,
        onRefresh = {
            viewModel.swipeRefresh()
        }
    ){
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp))
        {
            item {
                BusList {
                    locationString = it.locationName
                    viewModel.findTheBus(it)
                }
                Spacer(modifier = Modifier.height(20.dp))
//        InfoTextBox("Selected Bus: $")
//        Spacer(modifier = Modifier.height(20.dp))
                InfoTextBox("Number of passengers: ${state.value.passengers}")
                Spacer(modifier = Modifier.height(20.dp))
                InfoTextBox("Number of Seats available: ${state.value.seatsAvailable}")
                Spacer(modifier = Modifier.height(20.dp))
                MapScreen(state.value) {
                    //${it.latitude},${it.longitude}
                    if (locationString.length > 5){
                        openGMapWithQuery(locationString)
                    }
                    else{
                        openGMapWithQuery("${it.latitude},${it.longitude}")
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 10.dp , end = 10.dp)
    ){
        Text(
            text = "For Queries Contact us" ,
            color = Color.Blue ,
            modifier = Modifier
                .padding(top = 20.dp)
                .align(Alignment.BottomEnd)
                .clickable {
                    openContactDialog()
                }
        )
    }
}

@Composable
fun MapScreen(
    state: UiState,
    onClick : (Location)-> Unit
){

    var latitude by remember {
        mutableStateOf(state.latitude)
    }
    var longitude by remember {
        mutableStateOf(state.longitude)
    }

    LaunchedEffect(state.latitude,state.longitude){
        longitude = state.longitude
        latitude = state.latitude
    }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ){
        Text(text =  "Latitude" , modifier = Modifier.padding(10.dp).fillMaxWidth() , fontWeight = FontWeight.ExtraBold)
        TextField(
            value = latitude,
            onValueChange = {
                latitude = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(15.dp))
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(text =  "Longitude" , modifier = Modifier.padding(10.dp).fillMaxWidth() , fontWeight = FontWeight.ExtraBold)
        TextField(
            value = longitude,
            onValueChange = {
                longitude = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(15.dp))
        )
        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(15.dp))
                .width(150.dp)
                .height(40.dp)
                .border(
                    width = 1.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(15.dp)
                )
                .align(Alignment.CenterHorizontally)
                .clickable {
                    onClick(
                        Location(
                        latitude, longitude
                    )
                    )
                },
            contentAlignment = Alignment.Center
        ){
            Text(text = "Open Map")
        }
    }
}

@Composable
fun BusList(onItemClick:(Location)-> Unit){
    var isClicked by remember {
        mutableStateOf(false)
    }
    var selectedBus by remember {
        mutableStateOf("Select the Bus")
    }
    Box(
        modifier = Modifier
            .border(width = 1.dp , color = Color.Black , shape = RoundedCornerShape(10.dp))
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    isClicked = true
                }
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = selectedBus,
                textAlign = TextAlign.Start,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth(0.8f),
                maxLines = 1
            )
            Icon(
                imageVector = if (isClicked) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = null,
            )
        }
        DropdownMenu(
            expanded = isClicked,
            onDismissRequest = { isClicked = false}
        ){
            Constant.locations.forEachIndexed { index, location ->
                DropdownMenuItem(
                    text = {
                        Text(text = location.name)
                    },
                    onClick = {
                        selectedBus = location.name
                        onItemClick(location)
                        isClicked = false
                    }
                )
            }
        }
    }
}

@Composable
fun InfoTextBox(
    title : String
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            //.height(50.dp)
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = RoundedCornerShape(10.dp)
            )
    ){
        Text(text = title , modifier = Modifier.padding(10.dp))
    }
}
