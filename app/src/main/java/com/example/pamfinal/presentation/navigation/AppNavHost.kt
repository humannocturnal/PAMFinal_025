package com.example.pamfinal.presentation.navigation

import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import com.example.pamfinal.presentation.screens.DashboardScreen
import com.example.pamfinal.presentation.screens.LoginScreen
import com.example.pamfinal.presentation.screens.PegawaiListScreen
import com.example.pamfinal.presentation.screens.RegisterScreen
import com.example.pamfinal.presentation.viewmodel.AppViewModel
import com.example.pamfinal.presentation.viewmodel.AuthViewModel
import com.example.pamfinal.presentation.viewmodel.DashboardViewModel
import com.example.pamfinal.presentation.viewmodel.PegawaiViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pamfinal.data.remote.model.ApiConfig
import com.example.pamfinal.presentation.viewmodel.PegawaiViewModelFactory
import com.example.pamfinal.domain.repository.PegawaiRepositoryImpl
import com.example.pamfinal.domain.usecase.GetPegawaiUseCase
import com.example.pamfinal.domain.usecase.DeletePegawaiUseCase


@Composable
fun AppNavHost(
    appVm: AppViewModel,
    authVm: AuthViewModel,
    dashboardVm : DashboardViewModel
) {
    val nav = rememberNavController()
    val tokens by appVm.tokens.collectAsState()
    val authState by authVm.state.collectAsState()

    // === Repository & UseCase ===
    val pegawaiRepository = PegawaiRepositoryImpl(
        api = ApiConfig.pegawaiApi // sesuaikan dengan ApiConfig kamu
    )

    val getPegawaiUseCase = GetPegawaiUseCase(pegawaiRepository)
    val deletePegawaiUseCase = DeletePegawaiUseCase(pegawaiRepository)

// === Factory ===
    val pegawaiVm: PegawaiViewModel = viewModel(
        factory = PegawaiViewModelFactory(
            getPegawaiUseCase,
            deletePegawaiUseCase
        )
    )


    LaunchedEffect(tokens?.accessToken) {
        val isLoggedIn = !tokens?.accessToken.isNullOrBlank()
        if (isLoggedIn) {
            nav.navigate(Routes.LIST_PEGAWAI) {
                popUpTo(Routes.LOGIN) { inclusive = true }
            }
        }
    }

    NavHost(
        navController = nav,
        startDestination = if (tokens?.accessToken.isNullOrBlank()) Routes.LOGIN else Routes.DASHBOARD
    ) {
        composable(Routes.LOGIN) {
            LoginScreen (
                stateFlow = authVm.state,
                onLogin = authVm::login,
                onGoRegister = { nav.navigate(Routes.REGISTER) }
            )
        }
        composable(Routes.REGISTER) {
            RegisterScreen(
                stateFlow = authVm.state,
                onRegister = authVm::register,
                onBackToLogin = {
                    authVm.resetState()
                    nav.popBackStack()
                }
            )
        }

        composable(Routes.DASHBOARD) {
            DashboardScreen(
                title = "Dashboard",
                role = tokens?.role ?: "-",
                onLogout = { dashboardVm.logout() }
            )
        }
        composable(Routes.DASHBOARD_ADMIN) {
            DashboardScreen(
                title = "Dashboard Admin",
                role = tokens?.role ?: "admin",
                onLogout = { dashboardVm.logout() }
            )
        }
        composable(Routes.DASHBOARD_USER) {
            DashboardScreen(
                title = "Dashboard User",
                role = tokens?.role ?: "user",
                onLogout = { dashboardVm.logout() }
            )
        }
        composable(Routes.LIST_PEGAWAI) {
            PegawaiListScreen(
                vm = pegawaiVm,
                onAdd = {
                    nav.navigate(Routes.ADD_PEGAWAI)
                },
                onEdit = { pegawai ->
                    nav.navigate("${Routes.EDIT_PEGAWAI}/${pegawai.id_pegawai}")
                }
            )
        }
    }
}
