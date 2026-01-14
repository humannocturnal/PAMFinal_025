package com.example.pamfinal.utils

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

fun String.toRequestBody(): RequestBody =
    this.toRequestBody("text/plain".toMediaType())

fun File.toMultipart(name: String): MultipartBody.Part {
    val body = this.asRequestBody("image/*".toMediaType())
    return MultipartBody.Part.createFormData(name, this.name, body)
}
