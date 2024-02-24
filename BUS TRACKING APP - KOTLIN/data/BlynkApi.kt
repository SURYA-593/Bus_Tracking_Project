package com.demo.bustracking.data

import com.demo.bustracking.Constant
import com.demo.bustracking.data.dto.BlynkResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BlynkApi {

    @GET(Constant.URL_QUERY)
    suspend fun getIRSensorResult() : BlynkResponse
}