package com.lalabib.loginapp.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.lalabib.loginapp.domain.model.ListUser
import com.lalabib.loginapp.domain.model.User
import com.lalabib.loginapp.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val userUseCase: UserUseCase) :
    ViewModel() {

    fun getAllUser(): LiveData<PagingData<ListUser>> =
        userUseCase.getAllUser().cachedIn(viewModelScope).asLiveData()

    fun getUser(): LiveData<User> = userUseCase.getUser().asLiveData()

    fun logout() {
        viewModelScope.launch {
            userUseCase.logout()
        }
    }
}