package com.lalabib.loginapp.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.lalabib.loginapp.domain.model.ListUser
import com.lalabib.loginapp.domain.usecase.ListUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val listUserUseCase: ListUserUseCase) :
    ViewModel() {

    fun getAllUser(): LiveData<PagingData<ListUser>> =
        listUserUseCase.getAllUser().cachedIn(viewModelScope).asLiveData()
}