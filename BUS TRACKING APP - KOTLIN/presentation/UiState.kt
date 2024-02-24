package com.demo.bustracking.presentation

import com.demo.bustracking.Constant

data class UiState(
    val latitude : String = "",
    val longitude : String = "",
    val passengers : Int = 0,
    val seatsAvailable : Int = Constant.MAX_SEATS,
    val selectedBus : String = "",
    val isLoading : Boolean = false,
    val errorMessage : String? = null
)
