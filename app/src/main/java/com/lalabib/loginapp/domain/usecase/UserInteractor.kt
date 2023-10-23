package com.lalabib.loginapp.domain.usecase

import androidx.paging.PagingData
import com.lalabib.loginapp.data.remote.network.Result
import com.lalabib.loginapp.data.remote.response.LoginResponse
import com.lalabib.loginapp.domain.model.ListUser
import com.lalabib.loginapp.domain.model.User
import com.lalabib.loginapp.domain.repository.IUserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserInteractor @Inject constructor(private val listUserRepository: IUserRepository) :
    UserUseCase {
    override fun getAllUser(): Flow<PagingData<ListUser>> = listUserRepository.getAllUser()

    override fun postLogin(email: String, password: String): Flow<Result<LoginResponse>> =
        listUserRepository.postLogin(email, password)

    override suspend fun saveUser(user: User) = listUserRepository.saveUser(user)

    override fun getUser(): Flow<User> = listUserRepository.getUser()

    override suspend fun logout() = listUserRepository.logout()
}