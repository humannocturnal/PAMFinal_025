package com.example.pamfinal.data.repository

import com.example.pamfinal.data.remote.ApiService
import com.example.pamfinal.domain.repository.PegawaiRepository

class PegawaiRepositoryImpl(
    private val api: ApiService
) : PegawaiRepository {

    override suspend fun getPegawai() = api.getPegawai()

    override suspend fun deletePegawai(id: Int) =
        api.deletePegawai(id)
}
