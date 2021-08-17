package com.pog.passionfruitOrangeGuava.networking

import com.pog.passionfruitOrangeGuava.networking.apimodel.UserResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface PogApiService {

    @GET("users/{username}")
    suspend fun getUser(
        @Header("accept") accept: String = "application/vnd.github.v3+json",
        @Path("username") username: String
    ): UserResponse
}