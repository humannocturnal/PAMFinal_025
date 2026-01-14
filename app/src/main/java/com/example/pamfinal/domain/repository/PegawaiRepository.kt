package com.example.pamfinal.domain.repository

import com.example.pamfinal.domain.model.Pegawai
import java.io.File

interface PegawaiRepository {
    suspend fun getAll(): Result<List<Pegawai>>
    suspend fun delete(id: Int): Result<String>


    suspend fun createPegawai(
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
    ): Pegawai
}
