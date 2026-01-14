package com.example.pamfinal.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.pamfinal.domain.model.AuthTokens
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "auth_store")

class TokenDataStore(private val context: Context) {

    private val KEY_ACCESS = stringPreferencesKey("access_token")
    private val KEY_REFRESH = stringPreferencesKey("refresh_token")
    private val KEY_ROLE = stringPreferencesKey("role")

    suspend fun save(access: String, refresh: String?, role: String?) {
        context.dataStore.edit { pref ->
            pref[KEY_ACCESS] = access
            refresh?.let { pref[KEY_REFRESH] = it }
            role?.let { pref[KEY_ROLE] = it }
        }
    }

    fun observe(): Flow<AuthTokens?> {
        return context.dataStore.data.map { pref ->
            val access = pref[KEY_ACCESS]
            if (access.isNullOrBlank()) null
            else AuthTokens(
                accessToken = access,
                refreshToken = pref[KEY_REFRESH],
                role = pref[KEY_ROLE]
            )
        }
    }

    suspend fun clear() {
        context.dataStore.edit { it.clear() }
    }
}
