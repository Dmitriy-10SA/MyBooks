package com.andef.mybooks.presentation.find

import com.andef.mybooks.domain.entities.Book

sealed class FindScreenState {
    data object Initial : FindScreenState()
    data object Loading : FindScreenState()
    data object Error : FindScreenState()
    data class BookList(val bookList: List<Book>) : FindScreenState()
}