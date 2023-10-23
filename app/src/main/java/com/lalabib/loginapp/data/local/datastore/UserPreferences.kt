package com.lalabib.loginapp.data.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.lalabib.loginapp.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserPreferences @Inject constructor(private val dataStore: DataStore<Preferences>) {

    suspend fun saveUser(user: User) {
        dataStore.edit { preferences ->
            preferences[EMAIL] = user.email
            preferences[PASSWORD] = user.password
            preferences[TOKEN] = user.token
        }
    }

    fun getUser(): Flow<User> {
        return dataStore.data.map { preferences ->
            User(
                preferences[EMAIL] ?: "",
                preferences[PASSWORD] ?: "",
                preferences[TOKEN] ?: ""
            )
        }
    }

    suspend fun logout() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }
    companion object {

        private val EMAIL = stringPreferencesKey("email")
        private val PASSWORD = stringPreferencesKey("password")
        private val TOKEN = stringPreferencesKey("token")
    }
}