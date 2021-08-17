package com.pog.passionfruitOrangeGuava.dagger

import com.google.gson.GsonBuilder
import com.pog.passionfruitOrangeGuava.Constants
import com.pog.passionfruitOrangeGuava.networking.PogApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {
    @Provides
    fun provideAPIService(): PogApiService {
        val gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(Constants.BASE_URL)
            .build()
        return retrofit.create(PogApiService::class.java)
    }
}