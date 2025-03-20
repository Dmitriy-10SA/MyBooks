package com.andef.mybooks.presentation.find

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.andef.mybooks.R
import com.andef.mybooks.domain.entities.Book
import com.andef.mybooks.presentation.ViewModelFactory
import com.andef.mybooks.presentation.book.BooksVerticalGrid
import com.andef.mybooks.presentation.utils.CenteredLoadIndicator
import com.andef.mybooks.presentation.utils.CenteredText

//экран поиска книг
@Composable
fun FindScreen(
    paddingValues: PaddingValues,
    favouriteBooks: State<Set<Book>>,
    viewModelFactory: ViewModelFactory,
    onBookClickListener: (Book) -> Unit,
    onLikeClickListener: (Book) -> Unit
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
            onBookClickListener = onBookClickListener,
            onLikeClickListener = onLikeClickListener,
            favouriteBooks = favouriteBooks
        )
    }
}

@Composable
private fun BookList(
    modifier: Modifier,
    viewModel: FindScreenViewModel,
    favouriteBooks: State<Set<Book>>,
    query: State<String>,
    onBookClickListener: (Book) -> Unit,
    onLikeClickListener: (Book) -> Unit
) {
    val state = viewModel.state.collectAsState()

    when (val currentState = state.value) {
        is FindScreenState.BookList -> {
            BooksVerticalGrid(
                modifier = modifier,
                bookList = currentState.bookList,
                favouriteBooks = favouriteBooks,
                onBookClickListener = onBookClickListener,
                onLikeClickListener = onLikeClickListener
            )
        }

        FindScreenState.Initial -> {
            CenteredText(
                modifier = modifier,
                text = stringResource(R.string.input_title_book_looking_for)
            )
        }

        FindScreenState.Loading -> {
            CenteredLoadIndicator(modifier = modifier.fillMaxWidth())
        }

        FindScreenState.Error -> {
            ErrorMessage(modifier = modifier, viewModel = viewModel, query = query)
        }
    }
}

@Composable
private fun ErrorMessage(
    modifier: Modifier,
    viewModel: FindScreenViewModel,
    query: State<String>
) {
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BooksSearchBar(viewModel: FindScreenViewModel, query: MutableState<String>) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }

    SearchBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .focusRequester(focusRequester),
        shape = RoundedCornerShape(12.dp),
        colors = SearchBarDefaults.colors(
            containerColor = colorResource(R.color.very_light_gray),
            inputFieldColors = TextFieldDefaults.colors(cursorColor = colorResource(R.color.black))
        ),
        query = query.value,
        onQueryChange = {
            query.value = it
            if (query.value.isNotEmpty()) {
                viewModel.loadBookList(query.value, 2000)
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
            keyboardController?.hide()
            focusManager.clearFocus()
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
                text = stringResource(R.string.search),
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