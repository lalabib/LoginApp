package com.lalabib.loginapp.domain.repository

import androidx.paging.PagingData
import com.lalabib.loginapp.domain.model.ListUser
import kotlinx.coroutines.flow.Flow

interface IListUserRepository {
    fun getAllUser(): Flow<PagingData<ListUser>>
}