package com.example.pamfinal.domain.model

data class Pegawai(
    val id_pegawai: Int,
    val nik: String,
    val nama_pegawai: String,
    val alamat_ktp: String?,
    val domisili: String?,
    val no_wa: String?,
    val email: String,
    val jabatan: String?,
    val jenis_kelamin: String?,
    val status: String,
    val foto: String?,
    val tgl_lahir: String?
)
