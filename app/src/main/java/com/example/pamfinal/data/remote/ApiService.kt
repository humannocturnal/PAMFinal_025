package com.example.pamfinal.data.remote

import com.example.pamfinal.data.remote.model.LoginRequest
import com.example.pamfinal.data.remote.model.LoginResponse
import com.example.pamfinal.data.remote.model.PegawaiRequest
import com.example.pamfinal.data.remote.model.RegisterRequest
import com.example.pamfinal.data.remote.model.RegisterResponse
import com.example.pamfinal.domain.model.Pegawai
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService {

    @POST("login")
    suspend fun login(@Body req: LoginRequest): Response<LoginResponse>

    @POST("register")
    suspend fun register(@Body req: RegisterRequest): Response<RegisterResponse>


    @GET("pegawai")
    suspend fun getPegawai(): List<Pegawai>

    // =====================
    // CREATE (TAMBAH)
    // =====================
    @Multipart
    @POST("pegawai")
    suspend fun createPegawai(
        @Part("nik") nik: RequestBody,
        @Part("nama_pegawai") namaPegawai: RequestBody,
        @Part("alamat_ktp") alamatKtp: RequestBody,
        @Part("domisili") domisili: RequestBody,
        @Part("no_wa") noWa: RequestBody,
        @Part("email") email: RequestBody,
        @Part("jabatan") jabatan: RequestBody,
        @Part("jenis_kelamin") jenisKelamin: RequestBody,
        @Part("status") status: RequestBody,
        @Part("tgl_lahir") tglLahir: RequestBody,
        @Part foto: MultipartBody.Part
    ): Response<PegawaiResponse>


    // =====================
    // UPDATE (EDIT)
    // =====================
    @PUT("pegawai/{id}")
    suspend fun updatePegawai(
        @Path("id") id: Int,
        @Body request: PegawaiRequest
    ): Pegawai

    // =====================
    // DELETE
    // =====================
    @DELETE("pegawai/{id}")
    suspend fun deletePegawai(
        @Path("id") id: Int
    )
}