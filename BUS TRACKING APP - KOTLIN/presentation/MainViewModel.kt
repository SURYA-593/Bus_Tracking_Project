package com.demo.bustracking.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.bustracking.Constant
import com.demo.bustracking.Location
import com.demo.bustracking.data.Repository
import com.demo.bustracking.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo : Repository
) : ViewModel() {

    val state = MutableStateFlow(UiState())


    fun findTheBus(location: Location){
        viewModelScope.launch {
            val response = repo.getIRSensor()
            response.collectLatest {result->
                when(result){
                    is Resource.Error -> {
                        state.value = UiState(
                            errorMessage = result.message ?: "Unjnown Error"
                        )
                    }
                    is Resource.Loading -> {
                        state.value = UiState(
                            isLoading = true
                        )
                    }
                    is Resource.Success -> {
                        val asd = result.data
                        var count :Int =  asd ?: 0
//                        result.data?.collectLatest {
//                            count = it
//                        }
                        state.value = UiState(
                            latitude = location.latitude,
                            longitude = location.longitude,
                            passengers = count,
                            seatsAvailable = Constant.MAX_SEATS - count,
                            selectedBus = location.name
                        )
                    }
                }
            }
        }
    }

    fun swipeRefresh(){
        val location = Location(
            latitude = state.value.latitude,
            longitude = state.value.longitude,
            name = "",
            locationName = state.value.selectedBus
        )
        findTheBus(location)

    }
}