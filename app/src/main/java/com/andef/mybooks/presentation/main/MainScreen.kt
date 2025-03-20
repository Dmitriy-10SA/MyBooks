package com.andef.mybooks.presentation.main

import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import com.andef.mybooks.R
import com.andef.mybooks.domain.entities.Book
import com.andef.mybooks.navigation.AppNavGraph
import com.andef.mybooks.navigation.NavigationState
import com.andef.mybooks.navigation.Screen
import com.andef.mybooks.navigation.rememberNavigationState
import com.andef.mybooks.presentation.ViewModelFactory
import com.andef.mybooks.presentation.book.BookScreen
import com.andef.mybooks.presentation.book.FavouriteBooksScreen
import com.andef.mybooks.presentation.find.FindScreen
import com.andef.mybooks.presentation.utils.ChangeToastState
import com.andef.mybooks.ui.theme.MyBooksTheme

//главный экран приложения
@Composable
fun MainScreen(viewModelFactory: ViewModelFactory) {
    val viewModel: MainViewModel = viewModel(factory = viewModelFactory)
    val favouriteBooks = viewModel.favouriteBooks.collectAsState(emptySet())
    val toast = viewModel.toasts.collectAsState()
    val scope = rememberCoroutineScope()

    val navigationState = rememberNavigationState()
    val snackbarHostState = remember { SnackbarHostState() }
    val snackbarType = remember { mutableStateOf(MainSnackBarType.SUCCESS) }

    Scaffold(
        containerColor = colorResource(R.color.white),
        snackbarHost = {
            MainSnackbar(snackbarHostState = snackbarHostState, snackbarType = snackbarType.value)
        },
        topBar = {
            when (getCurrentDestinationRoute(navigationState)) {
                Screen.FavouriteScreenBookList.route -> {
                    MainTopBar(onBackButtonClickListener = {
                        navigationState.navigateTo(Screen.FindScreen.route)
                    })
                }
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
                FindScreen(
                    paddingValues = paddingValues,
                    viewModelFactory = viewModelFactory,
                    onBookClickListener = { book ->
                        navigationState.navigateToBookScreenFromFindScreen(book.id)
                    },
                    favouriteBooks = favouriteBooks,
                    onLikeClickListener = { book ->
                        onLikeClickAction(
                            viewModel = viewModel,
                            book = book,
                            favouriteBooks = favouriteBooks
                        )
                    }
                )
            },
            findScreenBookContent = { bookId ->
                BookScreen(
                    id = bookId,
                    paddingValues = paddingValues,
                    viewModelFactory = viewModelFactory,
                    onBackButtonClickListener = {
                        navigationState.navHostController.popBackStack()
                    },
                    favouriteBooks = favouriteBooks,
                    onLikeClickListener = { book ->
                        onLikeClickAction(
                            viewModel = viewModel,
                            book = book,
                            favouriteBooks = favouriteBooks
                        )
                    }
                )
            },
            favouriteScreenBookListContent = {
                FavouriteBooksScreen(
                    paddingValues = paddingValues,
                    onBookClickListener = { book ->
                        navigationState.navigateToBookScreenFromFavouriteScreen(book.id)
                    },
                    onLikeClickListener = { book ->
                        onLikeClickAction(
                            viewModel = viewModel,
                            book = book,
                            favouriteBooks = favouriteBooks
                        )
                    },
                    favouriteBooks = favouriteBooks
                )
            },
            favouriteScreenBookContent = { bookId ->
                BookScreen(
                    id = bookId,
                    paddingValues = paddingValues,
                    viewModelFactory = viewModelFactory,
                    onBackButtonClickListener = {
                        navigationState.navHostController.popBackStack()
                    },
                    favouriteBooks = favouriteBooks,
                    onLikeClickListener = { book ->
                        onLikeClickAction(
                            viewModel = viewModel,
                            book = book,
                            favouriteBooks = favouriteBooks
                        )
                    }
                )
            }
        )

        ChangeToastState(
            toasts = toast,
            snackbarHostState = snackbarHostState,
            scope = scope,
            snackbarType = snackbarType,
            initialToastAction = {
                viewModel.getInitialToast()
            }
        )
    }
}

private fun onLikeClickAction(
    viewModel: MainViewModel,
    book: Book,
    favouriteBooks: State<Set<Book>>
) {
    viewModel.addOrRemoveFavouriteBook(
        book = book,
        isFavourite = favouriteBooks.value.contains(book)
    )
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