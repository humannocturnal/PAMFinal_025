package com.example.pamfinal.data.remote

import com.example.pamfinal.domain.model.Pegawai

data class PegawaiListResponse(
    val data: List<Pegawai>
)

data class PegawaiResponse(
    val message: String,
    val data: Pegawai
)

data class MessageResponse(
    val message: String
)
