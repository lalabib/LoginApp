package com.lalabib.loginapp.data.remote

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.lalabib.loginapp.data.local.datastore.UserPreferences
import com.lalabib.loginapp.data.remote.network.Result
import com.lalabib.loginapp.data.remote.network.ApiService
import com.lalabib.loginapp.data.remote.response.LoginResponse
import com.lalabib.loginapp.domain.model.ListUser
import com.lalabib.loginapp.domain.model.User
import com.lalabib.loginapp.domain.repository.IUserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val pref: UserPreferences,
    private val apiService: ApiService,
): IUserRepository {

    override fun getAllUser(): Flow<PagingData<ListUser>> {
        return Pager(
            config = PagingConfig(
                pageSize = 2
            ),
            pagingSourceFactory = {
                ListUserPagingSource(apiService = apiService)
            }
        ).flow
    }

    override fun postLogin(email: String, password: String): Flow<Result<LoginResponse>> {
        return flow {
            emit(Result.Loading)
            try {
                val response = apiService.postLogin(email, password)
                emit(Result.Success(response))
            } catch (e: Exception) {
                emit(Result.Error(e.message.toString()))
                Log.e("PostLogin", "error: ${e.message.toString()}")
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun saveUser(user: User) {
        pref.saveUser(user)
    }

    override fun getUser(): Flow<User> {
        return pref.getUser()
    }
    override suspend fun logout() {
        pref.logout()
    }
}