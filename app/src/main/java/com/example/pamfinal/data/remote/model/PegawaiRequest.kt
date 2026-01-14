package com.example.pamfinal.data.remote.model

data class PegawaiRequest(
    val nik: String,
    val nama_pegawai: String,
    val alamat_ktp: String,
    val domisili: String,
    val no_wa: String,
    val email: String,
    val jabatan: String,
    val jenis_kelamin: String,
    val status: String = "aktif",
    val tgl_lahir: String
)