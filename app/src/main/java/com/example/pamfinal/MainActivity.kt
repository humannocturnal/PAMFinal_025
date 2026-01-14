package com.example.pamfinal

import androidx.activity.compose.setContent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.runtime.*
import com.example.pamfinal.di.AppModule
import com.example.pamfinal.domain.usecase.LoginUseCase
import com.example.pamfinal.domain.usecase.LogoutUseCase
import com.example.pamfinal.domain.usecase.ObserveTokensUseCase
import com.example.pamfinal.domain.usecase.RegisterUseCase
import com.example.pamfinal.domain.usecase.SaveTokensUseCase
import com.example.pamfinal.presentation.navigation.AppNavHost
import com.example.pamfinal.presentation.viewmodel.AppViewModel
import com.example.pamfinal.presentation.viewmodel.AuthViewModel
import com.example.pamfinal.presentation.viewmodel.DashboardViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repo = AppModule.provideAuthRepository(this)
        val loginUC = LoginUseCase(repo)
        val registerUC = RegisterUseCase(repo)
        val saveTokensUC = SaveTokensUseCase(repo)
        val observeTokensUC = ObserveTokensUseCase(repo)
        val logoutUC = LogoutUseCase(repo)

        setContent {
            val appVm = remember { AppViewModel(observeTokensUC) }
            val authVm = remember { AuthViewModel(loginUC, registerUC, saveTokensUC) }
            val dashboardVm = remember { DashboardViewModel(logoutUC) }

            LaunchedEffect(Unit) {
                appVm.startObserveTokens(this@MainActivity)
            }

            val tokens by appVm.tokens.collectAsState()

            AppNavHost(
                appVm = appVm,
                authVm = authVm ,
                dashboardVm = dashboardVm
            )
            LaunchedEffect(tokens) {
            }
        }
    }
}
