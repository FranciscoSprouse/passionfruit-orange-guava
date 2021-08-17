package com.pog.passionfruitOrangeGuava.networking.apimodel

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("login")
    val login: String,
    @SerializedName("avatar_url")
    val avatarURL: String,
    @SerializedName("public_repos")
    val publicRepos: Int
)