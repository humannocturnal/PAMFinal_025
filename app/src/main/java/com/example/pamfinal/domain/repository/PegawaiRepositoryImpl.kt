package com.example.pamfinal.domain.repository

import com.example.pamfinal.data.remote.PegawaiApi
import com.example.pamfinal.domain.model.Pegawai
import com.example.pamfinal.utils.toMultipart
import com.example.pamfinal.utils.toRequestBody
import java.io.File

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
    override suspend fun createPegawai(
        nik: String,
        namaPegawai: String,
        alamatKtp: String,
        domisili: String,
        noWa: String,
        email: String,
        jabatan: String,
        jenisKelamin: String,
        tglLahir: String,
        fotoFile: File
    ): Pegawai {

        val res = api.createPegawai(
            nik = nik.toRequestBody(),
            namaPegawai = namaPegawai.toRequestBody(),
            alamatKtp = alamatKtp.toRequestBody(),
            domisili = domisili.toRequestBody(),
            noWa = noWa.toRequestBody(), // ✅ HARUS noWa
            email = email.toRequestBody(),
            jabatan = jabatan.toRequestBody(),
            jenisKelamin = jenisKelamin.toRequestBody(),
            status = "aktif".toRequestBody(),
            tglLahir = tglLahir.toRequestBody(),
            foto = fotoFile.toMultipart("foto")
        )


        if (res.isSuccessful) {
            return res.body()?.data
                ?: throw Exception("Response pegawai kosong")
        } else {
            throw Exception("Gagal menambahkan pegawai")
        }
    }

}
