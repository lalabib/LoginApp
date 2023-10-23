package com.lalabib.loginapp.domain.usecase

import androidx.paging.PagingData
import com.lalabib.loginapp.domain.model.ListUser
import kotlinx.coroutines.flow.Flow

interface ListUserUseCase {
    fun getAllUser(): Flow<PagingData<ListUser>>
}