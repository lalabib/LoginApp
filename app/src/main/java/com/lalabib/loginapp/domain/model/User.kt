package com.lalabib.loginapp.domain.model

data class User (
    val email: String,
    val password: String,
    val token: String
)