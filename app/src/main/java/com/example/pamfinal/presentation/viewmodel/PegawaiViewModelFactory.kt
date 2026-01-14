package com.example.pamfinal.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pamfinal.domain.usecase.DeletePegawaiUseCase
import com.example.pamfinal.domain.usecase.GetPegawaiUseCase

class PegawaiViewModelFactory(
    private val getPegawaiUseCase: GetPegawaiUseCase,
    private val deletePegawaiUseCase: DeletePegawaiUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PegawaiViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PegawaiViewModel(
                getPegawai = getPegawaiUseCase,
                deletePegawai = deletePegawaiUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}