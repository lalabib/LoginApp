package com.lalabib.loginapp.data.remote.network

import com.lalabib.loginapp.data.remote.response.LoginResponse
import com.lalabib.loginapp.data.remote.response.UserResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @GET("api/users")
    suspend fun getListUser(@Query("page") page: Int): UserResponse

    @FormUrlEncoded
    @POST("api/login")
    suspend fun postLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse
}