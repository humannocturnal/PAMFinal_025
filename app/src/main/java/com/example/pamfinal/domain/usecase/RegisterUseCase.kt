package com.example.pamfinal.domain.usecase

import com.example.pamfinal.domain.repository.AuthRepository

class RegisterUseCase(private val repo: AuthRepository) {
    suspend operator fun invoke(nama: String, email: String, password: String, wa: String?) =
        repo.register(nama, email, password, wa)
}
