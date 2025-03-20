package com.andef.mybooks.presentation.main

import androidx.activity.compose.BackHandler
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import com.andef.mybooks.R
import com.andef.mybooks.navigation.AppNavGraph
import com.andef.mybooks.navigation.NavigationState
import com.andef.mybooks.navigation.Screen
import com.andef.mybooks.navigation.rememberNavigationState
import com.andef.mybooks.presentation.ViewModelFactory
import com.andef.mybooks.presentation.book.BookTopBar
import com.andef.mybooks.presentation.favourite.FavouriteScreenBookList
import com.andef.mybooks.presentation.find.FindScreenBookList
import com.andef.mybooks.ui.theme.MyBooksTheme

//главный экран приложения
@Composable
fun MainScreen(viewModelFactory: ViewModelFactory) {
    val navigationState = rememberNavigationState()
    val snackbarHostState = remember { SnackbarHostState() }
    val snackbarType = remember { mutableStateOf(MainSnackBarType.SUCCESS as MainSnackBarType) }

    Scaffold(
        containerColor = colorResource(R.color.white),
        snackbarHost = {
            MainSnackbar(snackbarHostState = snackbarHostState, snackbarType = snackbarType.value)
        },
        topBar = {
            val currentDestinationRoute = getCurrentDestinationRoute(navigationState)
            if (currentDestinationRoute == Screen.FindScreenBook.route
                || currentDestinationRoute == Screen.FavouriteScreenBook.route) {
                BookTopBar(
                    onBackButtonClickListener = {
                        navigationState.navHostController.popBackStack()
                    }
                )
            } else if (currentDestinationRoute == Screen.FavouriteScreenBookList.route) {
                BookTopBar(
                    title = stringResource(R.string.favourite),
                    onBackButtonClickListener = {
                        navigationState.navigateTo(Screen.FindScreen.route)
                    }
                )
            }
        },
        bottomBar = {
            val currentDestinationRoute = getCurrentDestinationRoute(navigationState)
            if (currentDestinationRoute != Screen.FindScreenBook.route
                && currentDestinationRoute != Screen.FavouriteScreenBook.route
            ) {
                MainBottomBar(navigationState = navigationState)
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
                    },
                    snackbarHostState = snackbarHostState,
                    snackbarType = snackbarType
                )
            },
            findScreenBookContent = {
                BackHandler {
                    navigationState.navHostController.popBackStack()
                }
            },
            favouriteScreenBookListContent = {
                FavouriteScreenBookList(
                    paddingValues = paddingValues,
                    viewModelFactory = viewModelFactory,
                    snackbarHostState = snackbarHostState,
                    snackbarType = snackbarType,
                    onBookClickListener = {
                        navigationState.navigateToBookScreenFromFavouriteScreen()
                    }
                )
            },
            favouriteScreenBookContent = {
                BackHandler {
                    navigationState.navHostController.popBackStack()
                }
            }
        )
    }
}

@Composable
private fun getCurrentDestinationRoute(navigationState: NavigationState): String? {
    val navBackStackEntry = navigationState.navHostController.currentBackStackEntryAsState()
    return navBackStackEntry.value?.destination?.route
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