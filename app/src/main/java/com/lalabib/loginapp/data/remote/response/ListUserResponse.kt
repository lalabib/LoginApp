package com.lalabib.loginapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class ListUserResponse(
    val id: Int,

    @field:SerializedName("first_name")
    val firstName: String,

    @field:SerializedName("last_name")
    val lastName: String,

    val avatar: String
)