package com.andef.mybooks.presentation.book

import androidx.compose.foundation.background
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.andef.mybooks.R
import com.andef.mybooks.domain.entities.Book
import com.andef.mybooks.presentation.utils.HeartInBox
import com.andef.mybooks.presentation.utils.UNKNOWN_BOOK_THUMBNAIL

//карточка книги
@Composable
fun BookCard(
    modifier: Modifier,
    book: Book,
    isFavourite: Boolean,
    onLikeClickListener: (Book) -> Unit
) {
    Box(modifier = modifier) {
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
                model = book.thumbnailLink ?: UNKNOWN_BOOK_THUMBNAIL,
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
        HeartInBox(
            modifier = Modifier
                .padding(top = 6.dp, end = 6.dp)
                .shadow(6.dp, CircleShape)
                .clip(CircleShape)
                .background(color = colorResource(R.color.white))
                .align(Alignment.TopEnd)
                .size(32.dp),
            isFavourite = isFavourite,
            book = book,
            onLikeClickListener = onLikeClickListener
        )
    }
}