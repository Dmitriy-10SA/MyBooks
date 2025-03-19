package com.andef.mybooks.presentation.book

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.andef.mybooks.R
import com.andef.mybooks.domain.entities.Book
import com.andef.mybooks.ui.theme.MyBooksTheme

@Composable
fun BookCard(book: Book, onBookClickListener: (Book) -> Unit) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                onBookClickListener(book)
            }
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AsyncImage(
            modifier = Modifier
                .height(260.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp)),
            model = book.thumbnailLink ?: UNKNOWN_THUMBNAIL,
            contentDescription = stringResource(R.string.cover_book),
            contentScale = ContentScale.FillBounds
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            fontSize = 14.sp,
            color = colorResource(R.color.dark_gray),
            text = book.authors?.joinToString(", ") ?: stringResource(R.string.unknown_author),
            textAlign = TextAlign.Start
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            fontSize = 14.sp,
            color = colorResource(R.color.black),
            text = book.title ?: stringResource(R.string.unknown_title),
            textAlign = TextAlign.Start
        )
    }
}

private const val UNKNOWN_THUMBNAIL =
    "https://books.google.ru/googlebooks/images/no_cover_thumb.gif"

@Preview
@Composable
private fun BookCardPreviewLight() {
    MyBooksTheme(darkTheme = false) {
        BookCard(TEST_BOOK) {}
    }
}

@Preview
@Composable
private fun BookCardPreviewDark() {
    MyBooksTheme(darkTheme = true) {
        BookCard(TEST_BOOK) {}
    }
}

private val TEST_BOOK = Book(
    id = "",
    authors = listOf("J.K. Rowling"),
    title = "Harry Potter and the Sorcerer's Stone",
    publishedYear = "2017",
    thumbnailLink = "http://books.google.com/books/content?id=4KR_kgAACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api",
    description = ""
)