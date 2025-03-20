package com.andef.mybooks.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

//граф навигации приложения
@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    findScreenBookListContent: @Composable () -> Unit,
    findScreenBookContent: @Composable (String) -> Unit,
    favouriteScreenBookListContent: @Composable () -> Unit,
    favouriteScreenBookContent: @Composable (String) -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.FindScreen.route
    ) {
        //элемент навигации "поиск"
        findScreenNavGraph(
            findScreenBookContent = findScreenBookContent,
            findScreenBookListContent = findScreenBookListContent
        )
        //элемент навигации "избранное"
        favouriteScreenNavGraph(
            favouriteScreenBookContent = favouriteScreenBookContent,
            favouriteScreenBookListContent = favouriteScreenBookListContent
        )
    }
}