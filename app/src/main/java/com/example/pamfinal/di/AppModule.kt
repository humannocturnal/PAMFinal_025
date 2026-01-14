package com.example.pamfinal.di

import android.content.Context
import com.example.pamfinal.data.local.TokenDataStore
import com.example.pamfinal.data.remote.model.ApiConfig
import com.example.pamfinal.data.remote.ApiService
import com.example.pamfinal.data.repository.AuthRepositoryImpl
import com.example.pamfinal.domain.repository.AuthRepository

object AppModule {

    @Volatile private var tokenCache: String? = null

    fun provideTokenStore(context: Context) = TokenDataStore(context)

    // api butuh tokenProvider
    fun provideApi(context: Context): ApiService {
        val store = provideTokenStore(context)
        // tokenCache akan diupdate dari observer di AppViewModel
        return ApiConfig.createApi { tokenCache }
    }

    fun provideAuthRepository(context: Context): AuthRepository {
        val api = provideApi(context)
        val store = provideTokenStore(context)
        return AuthRepositoryImpl(api, store)
    }

    fun updateTokenCache(token: String?) {
        tokenCache = token
    }
}
