package com.example.pamfinal.domain.repository

import com.example.pamfinal.domain.model.Pegawai

interface PegawaiRepository {
    suspend fun getAll(): Result<List<Pegawai>>
    suspend fun delete(id: Int): Result<String>
}
