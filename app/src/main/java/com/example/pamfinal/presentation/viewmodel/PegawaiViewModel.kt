package com.example.pamfinal.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pamfinal.domain.model.Pegawai
import com.example.pamfinal.domain.usecase.DeletePegawaiUseCase
import com.example.pamfinal.domain.usecase.GetPegawaiUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PegawaiViewModel(
    private val getPegawai: GetPegawaiUseCase,
    private val deletePegawai: DeletePegawaiUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<List<Pegawai>>(emptyList())
    val state: StateFlow<List<Pegawai>> = _state

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun loadPegawai() {
        viewModelScope.launch {
            _loading.value = true
            getPegawai()
                .onSuccess { _state.value = it }
                .onFailure { _error.value = it.message }
            _loading.value = false
        }
    }

    fun delete(id: Int) {
        viewModelScope.launch {
            deletePegawai(id)
            loadPegawai()
        }
    }
}
