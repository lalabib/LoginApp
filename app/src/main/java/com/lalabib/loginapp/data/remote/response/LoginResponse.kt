package com.lalabib.loginapp.data.remote.response

data class LoginResponse (
    val email: String,
    val password: String,
    val token: String
)