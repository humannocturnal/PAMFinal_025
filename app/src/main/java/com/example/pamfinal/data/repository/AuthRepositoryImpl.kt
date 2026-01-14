package com.example.pamfinal.data.repository

import com.example.pamfinal.data.local.TokenDataStore
import com.example.pamfinal.data.remote.ApiService
import com.example.pamfinal.data.remote.model.ErrorResponse
import com.example.pamfinal.data.remote.model.LoginRequest
import com.example.pamfinal.data.remote.model.RegisterRequest
import com.example.pamfinal.domain.model.AuthTokens
import com.example.pamfinal.domain.repository.AuthRepository
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow

class AuthRepositoryImpl(
    private val api: ApiService,
    private val tokenStore: TokenDataStore
) : AuthRepository {

    private fun parseErrorMsg(errorBody: String?): String {
        return try {
            val obj = Gson().fromJson(errorBody, ErrorResponse::class.java)
            obj.message ?: "Terjadi kesalahan"
        } catch (_: Exception) {
            "Terjadi kesalahan"
        }
    }

    override suspend fun login(email: String, password: String): Result<AuthTokens> {
        return try {
            val res = api.login(
                LoginRequest(
                    email_hrd = email.trim().lowercase(),
                    password = password
                )
            )
            if (res.isSuccessful) {
                val body = res.body()
                val access = body?.access_token
                if (access.isNullOrBlank()) return Result.failure(Exception("Token tidak ditemukan"))

                Result.success(
                    AuthTokens(
                        accessToken = access,
                        refreshToken = body.refresh_token,
                        role = body.role
                    )
                )
            } else {
                val msg = parseErrorMsg(res.errorBody()?.string())
                Result.failure(Exception(msg))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun register(
        nama: String,
        email: String,
        password: String,
        wa: String?
    ): Result<String> {
        return try {
            val res = api.register(
                RegisterRequest(
                    nama_hrd = nama,
                    email_hrd = email.trim().lowercase(),
                    password = password,
                    no_wa = wa?.takeIf { it.isNotBlank() }
                )
            )
            if (res.isSuccessful) {
                Result.success(res.body()?.message ?: "Register berhasil")
            } else {
                val msg = parseErrorMsg(res.errorBody()?.string())
                Result.failure(Exception(msg))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun saveTokens(tokens: AuthTokens) {
        tokenStore.save(tokens.accessToken, tokens.refreshToken, tokens.role)
    }

    override fun observeTokens(): Flow<AuthTokens?> = tokenStore.observe()

    override suspend fun clearTokens() {
        tokenStore.clear()
    }
}
