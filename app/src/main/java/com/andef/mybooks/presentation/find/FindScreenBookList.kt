package com.andef.mybooks.presentation.find

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.andef.mybooks.R
import com.andef.mybooks.domain.entities.Book
import com.andef.mybooks.presentation.ViewModelFactory
import com.andef.mybooks.presentation.book.BookCard
import com.andef.mybooks.ui.theme.MyBooksTheme

//экран поиска книг
@Composable
fun FindScreenBookList(
    paddingValues: PaddingValues,
    viewModelFactory: ViewModelFactory,
    onBookClickListener: (Book) -> Unit
) {
    val viewModel: FindScreenViewModel = viewModel(factory = viewModelFactory)
    val query = rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = paddingValues.calculateBottomPadding())
    ) {
        BooksSearchBar(viewModel = viewModel, query = query)
        BookList(
            modifier = Modifier.weight(1f),
            viewModel = viewModel,
            query = query,
            onBookClickListener = onBookClickListener
        )
    }
}

@Composable
private fun BookList(
    modifier: Modifier,
    viewModel: FindScreenViewModel,
    query: State<String>,
    onBookClickListener: (Book) -> Unit
) {
    val state = viewModel.state.collectAsState()

    when (val currentState = state.value) {
        is FindScreenState.BookList -> {
            BooksVerticalGrid(
                modifier = modifier,
                bookList = currentState.bookList,
                onBookClickListener = onBookClickListener
            )
        }

        FindScreenState.Initial -> {
            CenteredText(
                modifier = modifier,
                text = stringResource(R.string.input_title_book_looking_for)
            )
        }

        FindScreenState.Loading -> {
            LoadIndicator(modifier = modifier.fillMaxWidth())
        }

        FindScreenState.Error -> {
            ErrorMessage(modifier = modifier, viewModel = viewModel, query = query)
        }
    }
}

@Composable
private fun ErrorMessage(modifier: Modifier, viewModel: FindScreenViewModel, query: State<String>) {
    Column(
        modifier = modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.query_error_retry),
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                color = colorResource(R.color.black)
            )
            Spacer(modifier = Modifier.padding(6.dp))
            Button(
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.blue)
                ),
                onClick = {
                    viewModel.loadBookList(query.value)
                }
            ) {
                Text(
                    modifier = Modifier.padding(
                        top = 3.dp,
                        bottom = 3.dp,
                        start = 12.dp,
                        end = 12.dp
                    ),
                    text = stringResource(R.string.retry),
                    fontSize = 16.sp,
                    color = colorResource(R.color.white)
                )
            }
        }
    }
}

@Composable
private fun CenteredText(modifier: Modifier, text: String) {
    Column(
        modifier = modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = text,
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            color = colorResource(R.color.black)
        )
    }
}

@Composable
private fun BooksVerticalGrid(
    modifier: Modifier,
    bookList: List<Book>,
    onBookClickListener: (Book) -> Unit
) {
    if (bookList.isNotEmpty()) {
        LazyVerticalGrid(
            modifier = modifier,
            columns = GridCells.Fixed(2)
        ) {
            items(items = bookList, key = { it.id }) { book ->
                BookCard(book = book, onBookClickListener = onBookClickListener)
            }
        }
    } else {
        CenteredText(
            modifier = modifier,
            text = stringResource(R.string.nothing_was_found_for_your_query)
        )
    }
}

@Composable
private fun LoadIndicator(modifier: Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            color = colorResource(R.color.light_gray),
            trackColor = colorResource(R.color.black)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BooksSearchBar(viewModel: FindScreenViewModel, query: MutableState<String>) {
    SearchBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(12.dp),
        colors = SearchBarDefaults.colors(
            containerColor = colorResource(R.color.very_light_gray),
            inputFieldColors = TextFieldDefaults.colors(cursorColor = colorResource(R.color.black))
        ),
        query = query.value,
        onQueryChange = {
            query.value = it
            if (query.value.isNotEmpty()) {
                viewModel.loadBookList(query.value, 500)
            } else {
                viewModel.getInitialScreen()
            }
        },
        onSearch = { searchQuery ->
            if (query.value.isNotEmpty()) {
                viewModel.loadBookList(searchQuery.trim())
            } else {
                viewModel.getInitialScreen()
            }
        },
        active = false,
        onActiveChange = {},
        leadingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.magnifier),
                tint = colorResource(R.color.light_gray),
                contentDescription = stringResource(R.string.magnifying_glass_icon)
            )
        },
        placeholder = {
            Text(
                text = stringResource(R.string.search_hint),
                fontSize = 16.sp,
                color = colorResource(R.color.light_gray)
            )
        },
        trailingIcon = {
            if (query.value.isNotEmpty()) {
                IconButton(
                    onClick = {
                        query.value = ""
                        viewModel.getInitialScreen()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        tint = colorResource(R.color.light_gray),
                        contentDescription = stringResource(R.string.clear_input_text)
                    )
                }
            }
        },
        content = {}
    )
}

@Preview
@Composable
private fun FindScreenPreviewLight() {
    MyBooksTheme(darkTheme = false) {
        FindScreenBookList(TEST_PADDING_VALUES, TEST_VIEW_MODEL_FACTORY) {}
    }
}

@Preview
@Composable
private fun FindScreenPreviewDark() {
    MyBooksTheme(darkTheme = true) {
        FindScreenBookList(TEST_PADDING_VALUES, TEST_VIEW_MODEL_FACTORY) {}
    }
}

private val TEST_PADDING_VALUES = PaddingValues(0.dp)
private val TEST_VIEW_MODEL_FACTORY = ViewModelFactory(mapOf())