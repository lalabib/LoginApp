package com.lalabib.loginapp.domain.usecase

import androidx.paging.PagingData
import com.lalabib.loginapp.data.remote.network.Result
import com.lalabib.loginapp.data.remote.response.LoginResponse
import com.lalabib.loginapp.domain.model.ListUser
import com.lalabib.loginapp.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserUseCase {
    fun getAllUser(): Flow<PagingData<ListUser>>

    fun postLogin(email: String, password: String): Flow<Result<LoginResponse>>

    suspend fun saveUser(user: User)

    fun getUser(): Flow<User>

    suspend fun logout()
}