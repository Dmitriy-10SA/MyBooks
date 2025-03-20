package com.andef.mybooks.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
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

        //элемент навигации "избранное"
        navigation(
            route = Screen.FavouriteScreen.route,
            startDestination = Screen.FavouriteScreenBookList.route
        ) {
            //экран с избранными фильмами
            composable(
                route = Screen.FavouriteScreenBookList.route,
                enterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { it },
                        animationSpec = tween(1000)
                    ) + fadeIn(tween(1000))
                },
                exitTransition = {
                    slideOutHorizontally(
                        targetOffsetX = { it },
                        animationSpec = tween(1000)
                    ) + fadeOut(tween(1000))
                }
            ) {
                favouriteScreenBookListContent()
            }
            //экран с информацией о книге
            composable(
                route = Screen.FavouriteScreenBook.route,
                enterTransition = {
                    fadeIn(tween(1000))
                },
                exitTransition = {
                    fadeOut(tween(1000))
                }
            ) { navBackStack ->
                val bookId = navBackStack.arguments?.getString(Screen.BOOK_ID)!!
                favouriteScreenBookContent(bookId)
            }
        }
    }
}