package com.andef.mybooks.presentation.book

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.andef.mybooks.R
import com.andef.mybooks.domain.entities.Book
import com.andef.mybooks.presentation.utils.CenteredText

//список книг в две колонки
@Composable
fun BooksVerticalGrid(
    modifier: Modifier,
    favouriteBooks: State<Set<Book>>,
    ifEmptyText: String = stringResource(R.string.nothing_was_found_for_your_query),
    bookList: List<Book>,
    onBookClickListener: (Book) -> Unit,
    onLikeClickListener: (Book) -> Unit
) {
    if (bookList.isNotEmpty()) {
        LazyVerticalGrid(
            modifier = modifier,
            columns = GridCells.Fixed(2)
        ) {
            items(items = bookList, key = { it.id }) { book ->
                BookCard(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .clickable {
                            onBookClickListener(book)
                        }
                        .padding(8.dp)
                        .animateItem(),
                    book = book,
                    isFavourite = favouriteBooks.value.contains(book),
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