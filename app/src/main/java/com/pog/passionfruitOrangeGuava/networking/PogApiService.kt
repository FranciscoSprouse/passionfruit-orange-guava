package com.pog.passionfruitOrangeGuava.networking

import com.pog.passionfruitOrangeGuava.Constants.ACCEPT_PARAM
import com.pog.passionfruitOrangeGuava.networking.apimodel.UserResponse
import com.pog.passionfruitOrangeGuava.networking.apimodel.UserSearchResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface PogApiService {
    @GET("users/{username}")
    suspend fun getUser(
        @Header("accept") accept: String = ACCEPT_PARAM,
        @Path("username") username: String
    ): UserResponse

    @GET("search/users")
    suspend fun searchUsers(
        @Header("accept") accept: String = ACCEPT_PARAM,
        @Query("q") query: String
    ): UserSearchResponse
}