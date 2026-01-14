package com.example.pamfinal.domain.repository

import com.example.pamfinal.data.remote.PegawaiApi
import com.example.pamfinal.domain.model.Pegawai

class PegawaiRepositoryImpl(
    private val api: PegawaiApi
) : PegawaiRepository {

    override suspend fun getAll(): Result<List<Pegawai>> {
        return try {
            val res = api.getPegawai()
            if (res.isSuccessful) {
                Result.success(res.body()?.data ?: emptyList())
            } else {
                Result.failure(Exception("Gagal mengambil data pegawai"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun delete(id: Int): Result<String> {
        return try {
            val res = api.deletePegawai(id)
            if (res.isSuccessful) {
                Result.success(res.body()?.message ?: "Berhasil")
            } else {
                Result.failure(Exception("Gagal menghapus pegawai"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
