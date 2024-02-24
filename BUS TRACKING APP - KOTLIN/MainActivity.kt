package com.demo.bustracking

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.demo.bustracking.presentation.MainScreen
import com.demo.bustracking.presentation.MainViewModel

import com.demo.bustracking.presentation.theme.BusTrackingTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusTrackingTheme {
                // A surface container using the 'background' color from the theme
                val uri = "tel:${stringResource(R.string.phoneNumber)}"
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse(uri))
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    MainScreen(
                        openContactDialog = {
                            this@MainActivity.startActivity(intent)
                        },
                        openGMapWithQuery = {
                            openGMapWithQuery(query = it)
                        },
                        toast = {
                            toaster(it)
                        }
                    )
                }
            }
        }
    }

    fun toaster(message : String){
        Toast.makeText(
            this , message,Toast.LENGTH_LONG
        ).show()
    }


    // this function is used to open the map with location based on the longitude latitude
    fun openGMapWithQuery(latitude : String="0 ", longitude : String ="0",query : String ){
        val gmmIntentUri = Uri.parse("geo:$latitude,$longitude?q=$query")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps") // Use Google Maps app

        if (mapIntent.resolveActivity(this.packageManager) != null) {
            this.startActivity(mapIntent)
        } else {
            toaster("Install Google- Map For your Device...\uD83C\uDF0D \uD83C\uDF0E!")
        }
    }
}

@Composable
fun ErrorMessage(
    message : String
){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(message)
    }
}
@Composable
fun Loading(){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        CircularProgressIndicator()
    }
}