package com.andef.mybooks.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    findScreenBookListContent: @Composable () -> Unit,
    findScreenBookContent: @Composable () -> Unit,
    favouriteScreen: @Composable () -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.FindScreen.route
    ) {
        navigation(
            route = Screen.FindScreen.route,
            startDestination = Screen.FindScreenBookList.route
        ) {
            composable(Screen.FindScreenBookList.route) {
                findScreenBookListContent()
            }
            composable(Screen.FindScreenBook.route) {
                findScreenBookContent()
            }
        }
        composable(Screen.FavouriteScreen.route) {
            favouriteScreen()
        }
    }
}