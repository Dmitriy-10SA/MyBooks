package com.andef.mybooks.presentation.main

import androidx.activity.compose.BackHandler
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import com.andef.mybooks.R
import com.andef.mybooks.navigation.AppNavGraph
import com.andef.mybooks.navigation.Screen
import com.andef.mybooks.navigation.rememberNavigationState
import com.andef.mybooks.presentation.ViewModelFactory
import com.andef.mybooks.presentation.book.BookTopBar
import com.andef.mybooks.presentation.find.FindScreenBookList
import com.andef.mybooks.ui.theme.MyBooksTheme

@Composable
fun MainScreen(viewModelFactory: ViewModelFactory) {
    val navigationState = rememberNavigationState()

    Scaffold(
        containerColor = colorResource(R.color.white),
        topBar = {
            val navBackStackEntry = navigationState.navHostController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry.value?.destination
            if (currentDestination?.route == Screen.FindScreenBook.route) {
                BookTopBar(
                    onBackButtonClickListener = {
                        navigationState.navHostController.popBackStack()
                    }
                )
            }
        },
        bottomBar = {
            val navBackStackEntry = navigationState.navHostController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry.value?.destination
            if (currentDestination?.route != Screen.FindScreenBook.route) {
                MainBottomBar(navigationState)
            }
        }
    ) { paddingValues ->
        AppNavGraph(
            navHostController = navigationState.navHostController,
            findScreenBookListContent = {
                FindScreenBookList(
                    paddingValues = paddingValues,
                    viewModelFactory = viewModelFactory,
                    onBookClickListener = {
                        navigationState.navigateTo(Screen.FindScreenBook.route)
                    }
                )
            },
            findScreenBookContent = {
                BackHandler {
                    navigationState.navHostController.popBackStack()
                }
            },
            favouriteScreen = {

            }
        )
    }
}

@Preview
@Composable
private fun MainScreenPreviewLight() {
    MyBooksTheme(darkTheme = false) {
        MainScreen(TEST_VIEW_MODEL_FACTORY)
    }
}

@Preview
@Composable
private fun MainScreenPreviewDark() {
    MyBooksTheme(darkTheme = true) {
        MainScreen(TEST_VIEW_MODEL_FACTORY)
    }
}

private val TEST_VIEW_MODEL_FACTORY = ViewModelFactory(mapOf())