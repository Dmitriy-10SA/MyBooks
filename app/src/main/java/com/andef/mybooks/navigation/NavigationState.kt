package com.andef.mybooks.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

//состояние навигации
class NavigationState(val navHostController: NavHostController) {
    //навигация к указанному route с сохранение состояния и добавлением
    //в бэк-стек начальной вершины графа (экран поиска книг)
    fun navigateTo(route: String) {
        navHostController.navigate(route) {
            popUpTo(navHostController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun navigateToBookScreenFromFindScreen(bookId: String) {
        navHostController.navigate(
            Screen.FindScreenBook.FIND_SCREEN_BOOK_ROUTE + "/${Uri.encode(bookId)}"
        ) {
            popUpTo(Screen.FindScreenBookList.route) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun navigateToBookScreenFromFavouriteScreen(bookId: String) {
        navHostController.navigate(
            Screen.FavouriteScreenBook.FAVOURITE_SCREEN_BOOK_ROUTE + "/${Uri.encode(bookId)}"
        ) {
            popUpTo(Screen.FavouriteScreenBookList.route) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}

@Composable
fun rememberNavigationState(
    navHostController: NavHostController = rememberNavController()
): NavigationState {
    return remember {
        NavigationState(navHostController)
    }
}