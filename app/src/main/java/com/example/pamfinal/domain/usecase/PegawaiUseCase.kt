package com.example.pamfinal.domain.usecase

import com.example.pamfinal.domain.repository.PegawaiRepository

class GetPegawaiUseCase(private val repo: PegawaiRepository) {
    suspend operator fun invoke() = repo.getAll()
}

class DeletePegawaiUseCase(private val repo: PegawaiRepository) {
    suspend operator fun invoke(id: Int) = repo.delete(id)
}
