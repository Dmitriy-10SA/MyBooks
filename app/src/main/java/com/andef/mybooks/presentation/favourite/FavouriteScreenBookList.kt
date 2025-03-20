package com.andef.mybooks.presentation.favourite

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.andef.mybooks.domain.entities.Book
import com.andef.mybooks.presentation.ViewModelFactory
import com.andef.mybooks.presentation.book.BooksVerticalGrid
import com.andef.mybooks.presentation.main.MainSnackBarType
import com.andef.mybooks.presentation.utils.ChangeToastState

@Composable
fun FavouriteScreenBookList(
    paddingValues: PaddingValues,
    snackbarHostState: SnackbarHostState,
    snackbarType: MutableState<MainSnackBarType>,
    viewModelFactory: ViewModelFactory,
    onBookClickListener: (Book) -> Unit,
) {
    val viewModel: FavouriteScreenViewModel = viewModel(factory = viewModelFactory)
    val scope = rememberCoroutineScope()
    val toasts = viewModel.toasts.collectAsState()
    val favouriteBooks = viewModel.favouriteBooks.collectAsState(emptySet())
    BooksVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        ifEmptyText = "",
        bookList = favouriteBooks.value.toList(),
        favouriteBooks = favouriteBooks,
        onBookClickListener = onBookClickListener,
        onLikeClickListener = { book, isFavourite ->
            viewModel.addOrRemoveFavouriteBook(book, isFavourite)
        }
    )

    ChangeToastState(
        toasts = toasts,
        snackbarHostState = snackbarHostState,
        scope = scope,
        snackbarType = snackbarType,
        initialToastAction = {
            viewModel.getInitialToast()
        }
    )
}