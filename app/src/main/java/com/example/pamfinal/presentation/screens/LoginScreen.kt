package com.example.pamfinal.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.pamfinal.presentation.components.AuthCard
import com.example.pamfinal.presentation.components.GradientBackground
import com.example.pamfinal.presentation.components.PrimaryButton
import com.example.pamfinal.presentation.viewmodel.AuthUiState
import kotlinx.coroutines.flow.StateFlow
import org.w3c.dom.Text

@Composable
fun LoginScreen(
    stateFlow: StateFlow<AuthUiState>,
    onLogin: (String, String) -> Unit,
    onGoRegister: () -> Unit
) {
    val state by stateFlow.collectAsState()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val loading = state is AuthUiState.Loading

    GradientBackground {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {

            AuthCard(
                title = "Login HRD",
                subtitle = "Masuk menggunakan akun yang sudah terdaftar"
            ) {
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Email") },
                    singleLine = true
                )
                Spacer(Modifier.height(12.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Password") },
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation()
                )

                Spacer(Modifier.height(16.dp))

                PrimaryButton(
                    text = "Login",
                    loading = loading,
                    onClick = { onLogin(email, password) }
                )

                Spacer(Modifier.height(10.dp))

                TextButton(
                    onClick = onGoRegister,
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("Belum punya akun? Register")
                }

                Spacer(Modifier.height(8.dp))
                when (state) {
                    is AuthUiState.Error -> Text(
                        (state as AuthUiState.Error).msg,
                        color = MaterialTheme.colorScheme.error
                    )
                    is AuthUiState.Success -> Text(
                        (state as AuthUiState.Success).msg
                    )
                    else -> {}
                }
            }
        }
    }
}
