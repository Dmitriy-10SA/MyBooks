package com.andef.mybooks.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation

fun NavGraphBuilder.findScreenNavGraph(
    findScreenBookListContent: @Composable () -> Unit,
    findScreenBookContent: @Composable (String) -> Unit
) {
    //элемент навигации "поиск"
    navigation(
        route = Screen.FindScreen.route,
        startDestination = Screen.FindScreenBookList.route
    ) {
        //экран поиска книг
        composable(
            route = Screen.FindScreenBookList.route,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -it },
                    animationSpec = tween(1000)
                ) + fadeIn(tween(1000))
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -it },
                    animationSpec = tween(1000)
                ) + fadeOut(tween(1000))
            }
        ) {
            findScreenBookListContent()
        }
        //экран с информацией книге
        composable(
            route = Screen.FindScreenBook.route,
            enterTransition = {
                fadeIn(tween(1000))
            },
            exitTransition = {
                fadeOut(tween(1000))
            }
        ) { navBackStack ->
            val bookId = navBackStack.arguments?.getString(Screen.BOOK_ID)!!
            findScreenBookContent(bookId)
        }
    }
}