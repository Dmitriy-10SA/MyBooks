package com.andef.mybooks.presentation.book

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.andef.mybooks.R
import com.andef.mybooks.domain.entities.Book
import com.andef.mybooks.ui.theme.MyBooksTheme

//карточка книги
@Composable
fun BookCard(
    book: Book,
    isFavourite: Boolean,
    onBookClickListener: (Book) -> Unit,
    onLikeClickListener: (Book, Boolean) -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                onBookClickListener(book)
            }
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
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
            Spacer(modifier = Modifier.padding(4.dp))
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
        Box(
            modifier = Modifier
                .padding(top = 6.dp, end = 6.dp)
                .clip(CircleShape)
                .background(color = colorResource(R.color.white))
                .align(Alignment.TopEnd)
                .size(32.dp),
            contentAlignment = Alignment.Center
        ) {
            IconButton(
                onClick = {
                    onLikeClickListener(book, isFavourite)
                }
            ) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    imageVector = ImageVector.vectorResource(R.drawable.favourite),
                    tint = if (isFavourite) {
                        colorResource(R.color.red)
                    } else {
                        colorResource(R.color.light_gray)
                    },
                    contentDescription = stringResource(R.string.heart_icon)
                )
            }
        }
    }
}

//обложка для книги, которая не имеет своей обложки
private const val UNKNOWN_THUMBNAIL =
    "https://books.google.ru/googlebooks/images/no_cover_thumb.gif"

@Preview
@Composable
private fun BookCardPreviewLightFavourite() {
    MyBooksTheme(darkTheme = false) {
        BookCard(TEST_BOOK, true, {}) { b, a -> }
    }
}

@Preview
@Composable
private fun BookCardPreviewDarkNotFavourite() {
    MyBooksTheme(darkTheme = true) {
        BookCard(TEST_BOOK, false, {}) { b, a -> }
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