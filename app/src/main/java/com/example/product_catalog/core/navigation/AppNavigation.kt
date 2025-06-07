package com.example.product_catalog.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.example.product_catalog.features.catalog.CatalogScreen
import com.example.product_catalog.features.login.LoginScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ROUTES.LOGIN) {

        composable(ROUTES.LOGIN) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(ROUTES.CATALOG) {
                        popUpTo(ROUTES.LOGIN) { inclusive = true }
                    }
                }
            )
        }

        composable(ROUTES.CATALOG) {
            CatalogScreen(
                onLogout = {
                    navController.navigate(ROUTES.LOGIN) {
                        popUpTo(ROUTES.CATALOG) { inclusive = true }
                    }
                }
            )
        }
    }
}
