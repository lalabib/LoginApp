package com.lalabib.loginapp.domain.usecase

import androidx.paging.PagingData
import com.lalabib.loginapp.domain.model.ListUser
import com.lalabib.loginapp.domain.repository.IListUserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ListUserInteractor @Inject constructor(private val listUserRepository: IListUserRepository) :
    ListUserUseCase {
    override fun getAllUser(): Flow<PagingData<ListUser>> = listUserRepository.getAllUser()
}