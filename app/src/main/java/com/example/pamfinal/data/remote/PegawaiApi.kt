package com.example.pamfinal.data.remote

import retrofit2.Response
import retrofit2.http.*

interface PegawaiApi {

    @GET("pegawai")
    suspend fun getPegawai(): Response<PegawaiListResponse>

    @Multipart
    @POST("pegawai")
    suspend fun createPegawai(
        @PartMap data: Map<String, @JvmSuppressWildcards okhttp3.RequestBody>,
        @Part foto: okhttp3.MultipartBody.Part?
    ): Response<PegawaiResponse>

    @Multipart
    @PUT("pegawai/{id}")
    suspend fun updatePegawai(
        @Path("id") id: Int,
        @PartMap data: Map<String, @JvmSuppressWildcards okhttp3.RequestBody>,
        @Part foto: okhttp3.MultipartBody.Part?
    ): Response<PegawaiResponse>

    @DELETE("pegawai/{id}")
    suspend fun deletePegawai(
        @Path("id") id: Int
    ): Response<MessageResponse>
}
