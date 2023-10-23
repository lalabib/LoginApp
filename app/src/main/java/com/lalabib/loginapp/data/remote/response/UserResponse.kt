package com.lalabib.loginapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class UserResponse (
    @field:SerializedName("data")
    val data: List<ListUserResponse>
)