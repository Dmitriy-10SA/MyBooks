package com.andef.mybooks.presentation.book

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.andef.mybooks.R
import com.andef.mybooks.domain.entities.Book
import com.andef.mybooks.presentation.utils.HeartInBox

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookTopBar(
    book: Book? = null,
    favouriteBooks: State<Set<Book>>? = null,
    onBackButtonClickListener: () -> Unit,
    onLikeClickListener: (Book) -> Unit = {},
    errorLoad: Boolean = false
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(R.color.white),
            navigationIconContentColor = colorResource(R.color.black)
        ),
        title = {},
        actions = {
            if (!errorLoad) {
                HeartInBox(
                    modifier = Modifier
                        .padding(top = 6.dp, end = 6.dp)
                        .shadow(6.dp, CircleShape)
                        .clip(CircleShape)
                        .background(color = colorResource(R.color.white))
                        .size(32.dp),
                    book = book!!,
                    isFavourite = favouriteBooks!!.value.contains(book),
                    onLikeClickListener = onLikeClickListener
                )
            }
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    onBackButtonClickListener()
                }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.back)
                )
            }
        }
    )
}