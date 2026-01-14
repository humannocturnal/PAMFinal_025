package com.example.pamfinal.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.pamfinal.domain.model.Pegawai
import com.example.pamfinal.presentation.viewmodel.PegawaiViewModel

@Composable
fun PegawaiListScreen(
    vm: PegawaiViewModel,
    onAdd: () -> Unit,
    onEdit: (Pegawai) -> Unit
) {
    val pegawai by vm.state.collectAsState()
    val loading by vm.loading.collectAsState()

    LaunchedEffect(Unit) {
        vm.loadPegawai()
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAdd) {
                Icon(Icons.Default.Add, contentDescription = "Tambah")
            }
        }
    ) { padding ->

        if (loading) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                items(pegawai) { p ->
                    PegawaiItem(
                        pegawai = p,
                        onEdit = { onEdit(p) },
                        onDelete = { vm.delete(p.id_pegawai) }
                    )
                }
            }
        }
    }
}
