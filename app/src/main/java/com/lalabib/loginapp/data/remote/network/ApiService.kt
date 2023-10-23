package com.lalabib.loginapp.data.remote.network

import com.lalabib.loginapp.data.remote.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/users")
    suspend fun getListUser(@Query("page") page: Int): UserResponse
}