package com.demo.bustracking.data

import androidx.compose.runtime.collectAsState
import com.demo.bustracking.Constant
import com.demo.bustracking.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import retrofit2.Call

class Repository @Inject constructor(
    val api : BlynkApi,
){

    suspend fun get(){
        for (i in 1..10){
            println("hellow")
        }
    }

    suspend fun getIRSensor():Flow<Resource<Int>> = flow {
            try {
                emit(Resource.Loading())
                val irValue = api.getIRSensorResult()
                //irValue.collectLatest {
                    emit(Resource.Success(irValue.v1))
            }
            catch (http : HttpException){
                emit(Resource.Error(message = http.localizedMessage ?: "Unknown Error"))
            }
            catch (io : IOException){
                emit(Resource.Error(message = "Turn On Your InterNet Connections"))
            }
            catch (e : Exception){
                emit(Resource.Error(e.localizedMessage ?: "Unknown Error"))
            }
        }
}