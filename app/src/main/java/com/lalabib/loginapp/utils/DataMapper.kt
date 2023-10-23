package com.lalabib.loginapp.utils

import com.lalabib.loginapp.data.remote.response.ListUserResponse
import com.lalabib.loginapp.domain.model.ListUser

object DataMapper {

    fun responseToDomain(response: List<ListUserResponse>): List<ListUser> {
        return response.map {
            ListUser(
                id = it.id,
                firstName = it.firstName,
                lastName = it.lastName,
                avatar = it.avatar
            )
        }
    }
}