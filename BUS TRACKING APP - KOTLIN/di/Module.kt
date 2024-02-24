package com.demo.bustracking.di

import com.demo.bustracking.Constant
import com.demo.bustracking.data.BlynkApi
import com.demo.bustracking.data.Repository
import com.google.firebase.messaging.FirebaseMessagingService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    @Singleton
    fun provideBlynkApi():BlynkApi{
        return Retrofit.Builder()
            .baseUrl(Constant.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BlynkApi::class.java)
    }

    @Provides
    fun provideFirebaseMessagingService(): FirebaseMessagingService {
        return FirebaseMessagingService()
    }


    @Provides
    @Singleton
    fun provideRepository(api : BlynkApi):Repository{
        return Repository(api)
    }

}