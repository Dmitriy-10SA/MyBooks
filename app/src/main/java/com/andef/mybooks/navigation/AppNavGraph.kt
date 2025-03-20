package com.andef.mybooks.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation

//граф навигации приложения
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
        //элемент навигации "поиск"
        navigation(
            route = Screen.FindScreen.route,
            startDestination = Screen.FindScreenBookList.route
        ) {
            //экран поиска книг
            composable(Screen.FindScreenBookList.route) {
                findScreenBookListContent()
            }
            //экран с информацией книге
            composable(Screen.FindScreenBook.route) {
                findScreenBookContent()
            }
        }
        //элемент навигации "избранное", экран с избранными фильмами
        composable(Screen.FavouriteScreen.route) {
            favouriteScreen()
        }
    }
}