package com.andef.mybooks.presentation.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.andef.mybooks.R
import com.andef.mybooks.domain.entities.Book

@Composable
fun HeartInBox(
    modifier: Modifier = Modifier,
    isFavourite: Boolean,
    book: Book,
    onLikeClickListener: (Book) -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        IconButton(
            onClick = {
                onLikeClickListener(book)
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