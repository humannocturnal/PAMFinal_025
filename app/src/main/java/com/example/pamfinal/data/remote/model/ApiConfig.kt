package com.example.pamfinal.data.remote.model

import com.example.pamfinal.data.remote.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {

    // Android emulator -> host machine = 10.0.2.2
    private const val BASE_URL = "http://10.0.2.2:5000/"

    fun createApi(tokenProvider: () -> String?): ApiService {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val authInterceptor = okhttp3.Interceptor { chain ->
            val req = chain.request()
            val token = tokenProvider()
            val newReq = if (!token.isNullOrBlank()) {
                req.newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
            } else req
            chain.proceed(newReq)
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
