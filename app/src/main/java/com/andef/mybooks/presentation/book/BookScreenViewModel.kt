package com.andef.mybooks.presentation.book

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andef.mybooks.domain.usecases.database.GetBookByIdUseCase
import com.andef.mybooks.domain.usecases.network.GetBookListUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BookScreenViewModel @Inject constructor(
    private val getBookByIdUseCase: GetBookByIdUseCase,
    private val getBookListUseCase: GetBookListUseCase
) : ViewModel() {
    //состояние экрана с информацией о книге
    private val _state = MutableStateFlow(BookScreenState.Initial as BookScreenState)
    val state: StateFlow<BookScreenState> = _state

    //загрузка книги через интернет произошла с ошибкой
    private val networkExceptionHandler = CoroutineExceptionHandler { _, e ->
        Log.d(TAG, e.toString())
        _state.value = BookScreenState.Error
    }

    //загрузка книги по id
    fun loadBookById(id: String) {
        viewModelScope.launch(networkExceptionHandler) {
            try {
                _state.value = BookScreenState.Loading
                val book = withContext(Dispatchers.IO) {
                    getBookByIdUseCase.execute(id)
                }
                _state.value = BookScreenState.BookLoad(book)
            } catch (e: Exception) {
                //книга не найдена в базе данных избранных книг
                //или произошла ошибка, пробуем грузить из интернета
                Log.d(TAG, e.toString())
                val book = withContext(Dispatchers.IO) {
                    getBookListUseCase.execute(id)
                }
                if (book.isNotEmpty()) {
                    val currentBook = book.find { it.id == id }
                    currentBook?.let {
                        _state.value = BookScreenState.BookLoad(it)
                    } ?: throw Exception()
                } else {
                    throw Exception()
                }
            }
        }
    }

    companion object {
        private const val TAG = "BookScreenViewModel"
    }
}