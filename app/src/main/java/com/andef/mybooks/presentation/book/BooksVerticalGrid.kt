package com.andef.mybooks.presentation.book

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.andef.mybooks.R
import com.andef.mybooks.domain.entities.Book
import com.andef.mybooks.presentation.utils.CenteredText

@Composable
fun BooksVerticalGrid(
    modifier: Modifier,
    favouriteBooks: State<Set<Book>>,
    ifEmptyText: String = stringResource(R.string.nothing_was_found_for_your_query),
    bookList: List<Book>,
    onBookClickListener: (Book) -> Unit,
    onLikeClickListener: (Book, Boolean) -> Unit
) {
    if (bookList.isNotEmpty()) {
        LazyVerticalGrid(
            modifier = modifier,
            columns = GridCells.Fixed(2)
        ) {
            items(items = bookList, key = { it.id }) { book ->
                BookCard(
                    book = book,
                    isFavourite = favouriteBooks.value.contains(book),
                    onBookClickListener = onBookClickListener,
                    onLikeClickListener = onLikeClickListener
                )
            }
        }
    } else {
        CenteredText(
            modifier = modifier,
            text = ifEmptyText
        )
    }
}