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
    findScreenBookContent: @Composable (String) -> Unit,
    favouriteScreenBookListContent: @Composable () -> Unit,
    favouriteScreenBookContent: @Composable (String) -> Unit
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
            composable(Screen.FindScreenBook.route) { navBackStack ->
                val bookId = navBackStack.arguments?.getString(Screen.BOOK_ID)!!
                findScreenBookContent(bookId)
            }
        }

        //элемент навигации "избранное"
        navigation(
            route = Screen.FavouriteScreen.route,
            startDestination = Screen.FavouriteScreenBookList.route
        ) {
            //экран с избранными фильмами
            composable(Screen.FavouriteScreenBookList.route) {
                favouriteScreenBookListContent()
            }
            //экран с информацией о книге
            composable(Screen.FavouriteScreenBook.route) { navBackStack ->
                val bookId = navBackStack.arguments?.getString(Screen.BOOK_ID)!!
                favouriteScreenBookContent(bookId)
            }
        }
    }
}