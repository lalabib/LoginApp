package com.lalabib.loginapp.domain.model

import com.google.gson.annotations.SerializedName

data class ListUser (
    val id: Int,

    @field:SerializedName("first_name")
    val firstName: String,

    @field:SerializedName("last_name")
    val lastName: String,

    val avatar: String
)