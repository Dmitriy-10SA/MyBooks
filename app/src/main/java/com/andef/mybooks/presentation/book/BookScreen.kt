package com.andef.mybooks.presentation.book

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.andef.mybooks.R
import com.andef.mybooks.domain.entities.Book
import com.andef.mybooks.presentation.ViewModelFactory
import com.andef.mybooks.presentation.utils.CenteredLoadIndicator
import com.andef.mybooks.presentation.utils.CenteredText
import com.andef.mybooks.presentation.utils.UNKNOWN_BOOK_THUMBNAIL

//экран с информацией о книге
@Composable
fun BookScreen(
    id: String,
    paddingValues: PaddingValues,
    favouriteBooks: State<Set<Book>>,
    viewModelFactory: ViewModelFactory,
    onBackButtonClickListener: () -> Unit,
    onLikeClickListener: (Book) -> Unit
) {
    val viewModel: BookScreenViewModel = viewModel(factory = viewModelFactory)
    val state = viewModel.state.collectAsState()

    LaunchedEffect(id) {
        viewModel.loadBookById(id)
    }

    when (val currentState = state.value) {
        is BookScreenState.BookLoad -> {
            BookInfoScreen(
                book = currentState.book,
                favouriteBooks = favouriteBooks,
                paddingValues = paddingValues,
                onBackButtonClickListener = onBackButtonClickListener,
                onLikeClickListener = onLikeClickListener
            )
        }

        BookScreenState.Error -> {
            ErrorScreen(
                paddingValues = paddingValues,
                onBackButtonClickListener = onBackButtonClickListener
            )
        }

        BookScreenState.Initial -> {
            LoadScreen(
                paddingValues = paddingValues,
                onBackButtonClickListener = onBackButtonClickListener
            )
        }

        BookScreenState.Loading -> {
            LoadScreen(
                paddingValues = paddingValues,
                onBackButtonClickListener = onBackButtonClickListener
            )
        }
    }
}

@Composable
private fun LoadScreen(paddingValues: PaddingValues, onBackButtonClickListener: () -> Unit) {
    Scaffold(
        containerColor = colorResource(R.color.white),
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = paddingValues.calculateBottomPadding()),
        topBar = {
            BookTopBar(
                onBackButtonClickListener = onBackButtonClickListener,
                errorLoad = true
            )
        }
    ) { innerScaffoldPaddingValues ->
        CenteredLoadIndicator(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerScaffoldPaddingValues)
        )
    }
}

@Composable
private fun ErrorScreen(paddingValues: PaddingValues, onBackButtonClickListener: () -> Unit) {
    Scaffold(
        containerColor = colorResource(R.color.white),
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = paddingValues.calculateBottomPadding()),
        topBar = {
            BookTopBar(
                onBackButtonClickListener = onBackButtonClickListener,
                errorLoad = true
            )
        }
    ) { innerScaffoldPaddingValues ->
        CenteredText(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerScaffoldPaddingValues),
            text = stringResource(R.string.exception_when_get_book_by_id)
        )
        BackHandler {
            onBackButtonClickListener()
        }
    }
}

@Composable
private fun BookInfoScreen(
    book: Book,
    favouriteBooks: State<Set<Book>>,
    paddingValues: PaddingValues,
    onBackButtonClickListener: () -> Unit,
    onLikeClickListener: (Book) -> Unit
) {
    Scaffold(
        containerColor = colorResource(R.color.white),
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = paddingValues.calculateBottomPadding()),
        topBar = {
            BookTopBar(
                book = book,
                favouriteBooks = favouriteBooks,
                onBackButtonClickListener = onBackButtonClickListener,
                onLikeClickListener = onLikeClickListener
            )
        }
    ) { innerScaffoldPaddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerScaffoldPaddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(4.dp))
            AsyncImage(
                modifier = Modifier
                    .height(300.dp)
                    .width(200.dp)
                    .clip(RoundedCornerShape(16.dp)),
                model = book.thumbnailLink ?: UNKNOWN_BOOK_THUMBNAIL,
                contentDescription = stringResource(R.string.cover_book),
                contentScale = ContentScale.FillBounds
            )
            Spacer(modifier = Modifier.padding(6.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                fontSize = 16.sp,
                color = colorResource(R.color.dark_gray),
                text = book.authors?.joinToString(", ") ?: stringResource(R.string.unknown_author),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.padding(2.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                fontSize = 16.sp,
                color = colorResource(R.color.black),
                text = book.title ?: stringResource(R.string.unknown_title),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.padding(2.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                fontSize = 14.sp,
                color = colorResource(R.color.dark_gray),
                text = book.publishedYear ?: stringResource(R.string.published_year_unknown),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Card(
                modifier = Modifier.weight(1f),
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(R.color.very_light_gray),
                    contentColor = colorResource(R.color.black)
                ),
                shape = RoundedCornerShape(32.dp),
                elevation = CardDefaults.elevatedCardElevation(12.dp)
            ) {
                LazyColumn(modifier = Modifier.padding(16.dp)) {
                    item {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(R.string.description),
                            fontSize = 16.sp,
                        )
                        Spacer(modifier = Modifier.padding(8.dp))
                    }
                    item {
                        Text(
                            text = book.description ?: stringResource(R.string.empty_description),
                            fontSize = 14.sp,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
        BackHandler {
            onBackButtonClickListener()
        }
    }
}