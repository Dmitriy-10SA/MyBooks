package com.andef.mybooks.presentation.book

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import com.andef.mybooks.domain.entities.Book

//экран со списком избранных книг
@Composable
fun FavouriteBooksScreen(
    paddingValues: PaddingValues,
    favouriteBooks: State<Set<Book>>,
    onBookClickListener: (Book) -> Unit,
    onLikeClickListener: (Book) -> Unit
) {
    BooksVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        ifEmptyText = "",
        bookList = favouriteBooks.value.toList(),
        favouriteBooks = favouriteBooks,
        onBookClickListener = onBookClickListener,
        onLikeClickListener = onLikeClickListener
    )
}