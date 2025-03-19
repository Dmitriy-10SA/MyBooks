package com.andef.mybooks.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    findScreenContent: @Composable () -> Unit,
    favouriteScreen: @Composable () -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.FindScreen
    ) {
        composable(Screen.FindScreen.route) {
            findScreenContent()
        }
        composable(Screen.FavouriteScreen.route) {
            favouriteScreen()
        }
    }
}