package com.mcmouse88.mvicomposeexample.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mcmouse88.mvicomposeexample.presentation.screen.auth.login.AuthScreen
import com.mcmouse88.mvicomposeexample.presentation.screen.auth.sign_up.SignUpScreen
import com.mcmouse88.mvicomposeexample.presentation.screen.main.MainScreen

sealed class Screens(val route: String) {
    object MainScreenType : Screens(route = "main_screen")
    object AuthScreenType : Screens(route = "auth_screen")
    object RegisterScreenType : Screens(route = "register_screen")
}

@Composable
fun SetupNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.AuthScreenType.route
    ) {
        composable(route = Screens.MainScreenType.route) {
            MainScreen(navController = navController)
        }

        composable(route = Screens.AuthScreenType.route) {
            AuthScreen(navController = navController)
        }

        composable(route = Screens.RegisterScreenType.route) {
            SignUpScreen(navController = navController)
        }
    }
}