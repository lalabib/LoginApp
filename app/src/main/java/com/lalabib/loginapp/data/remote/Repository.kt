package com.lalabib.loginapp.data.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.lalabib.loginapp.data.remote.network.ApiService
import com.lalabib.loginapp.domain.model.ListUser
import com.lalabib.loginapp.domain.repository.IListUserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val apiService: ApiService,
): IListUserRepository {

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
}