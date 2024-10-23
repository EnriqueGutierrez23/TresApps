package com.example.tresapps.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.example.tresapps.screen.SecondScreen
import com.example.tresapps.screen.ThirdScreen
import com.example.tresapps.screen.FirstScreen


sealed class AppScreen(val route: String) {
    object FirstScreen : AppScreen(route = "First_Screen")
    object SecondScreen : AppScreen(route = "Second_Screen")
    object ThirdScreen : AppScreen(route = "third_Screen")
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppScreen.FirstScreen.route) {
        composable(AppScreen.FirstScreen.route) { FirstScreen(navController) }
        composable(AppScreen.SecondScreen.route) { SecondScreen(navController) }
        composable(AppScreen.ThirdScreen.route) { ThirdScreen(navController) }
    }
}
