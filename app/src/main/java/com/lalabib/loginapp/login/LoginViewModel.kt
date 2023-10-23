package com.lalabib.loginapp.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.lalabib.loginapp.domain.model.User
import com.lalabib.loginapp.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val userUseCase: UserUseCase) :
    ViewModel() {

    fun postLogin(email: String, password: String) = userUseCase.postLogin(email, password).asLiveData()

    fun saveUser(user: User) {
        viewModelScope.launch {
            userUseCase.saveUser(user)
        }
    }
}