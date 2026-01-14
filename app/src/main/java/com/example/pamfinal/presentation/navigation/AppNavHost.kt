package com.example.pamfinal.presentation.navigation

import androidx.compose.runtime.*
import androidx.navigation.compose.*
import com.example.pamfinal.presentation.screens.DashboardScreen
import com.example.pamfinal.presentation.screens.LoginScreen
import com.example.pamfinal.presentation.screens.RegisterScreen
import com.example.pamfinal.presentation.viewmodel.AppViewModel
import com.example.pamfinal.presentation.viewmodel.AuthUiState
import com.example.pamfinal.presentation.viewmodel.AuthViewModel
import com.example.pamfinal.presentation.viewmodel.DashboardViewModel

@Composable
fun AppNavHost(
    appVm: AppViewModel,
    authVm: AuthViewModel,
    dashboardVm : DashboardViewModel
) {
    val nav = rememberNavController()
    val tokens by appVm.tokens.collectAsState()
    val authState by authVm.state.collectAsState()



    LaunchedEffect(tokens?.accessToken) {
        val isLoggedIn = !tokens?.accessToken.isNullOrBlank()

        if (isLoggedIn) {
            val role = tokens?.role?.lowercase()
            val dest = when (role) {
                "admin" -> Routes.DASHBOARD_ADMIN
                "user" -> Routes.DASHBOARD_USER
                else -> Routes.DASHBOARD // default
            }
            nav.navigate(dest) {
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
    }
}
