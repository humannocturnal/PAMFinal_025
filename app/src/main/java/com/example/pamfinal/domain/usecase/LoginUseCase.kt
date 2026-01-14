package com.example.pamfinal.domain.usecase

import com.example.pamfinal.domain.repository.AuthRepository

class LoginUseCase(private val repo: AuthRepository) {
    suspend operator fun invoke(email: String, password: String) = repo.login(email, password)
}
