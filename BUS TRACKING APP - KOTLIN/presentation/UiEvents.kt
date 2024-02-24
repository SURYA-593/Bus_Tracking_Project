package com.demo.bustracking.presentation

import com.demo.bustracking.Location

sealed class UiEvents{
    data class FindTheBus(val location: Location) : UiEvents()

}
