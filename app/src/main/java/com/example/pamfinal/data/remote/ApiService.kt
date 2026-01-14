package com.example.pamfinal.data.remote

import com.example.pamfinal.data.remote.model.LoginRequest
import com.example.pamfinal.data.remote.model.LoginResponse
import com.example.pamfinal.data.remote.model.RegisterRequest
import com.example.pamfinal.data.remote.model.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("login")
    suspend fun login(@Body req: LoginRequest): Response<LoginResponse>

    @POST("register")
    suspend fun register(@Body req: RegisterRequest): Response<RegisterResponse>
}