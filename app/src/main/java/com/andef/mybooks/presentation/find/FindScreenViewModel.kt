package com.andef.mybooks.presentation.find

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andef.mybooks.domain.entities.Book
import com.andef.mybooks.domain.usecases.database.AddFavouriteBookUseCase
import com.andef.mybooks.domain.usecases.database.GetFavouriteBooksUseCase
import com.andef.mybooks.domain.usecases.database.RemoveFavouriteBookUseCase
import com.andef.mybooks.domain.usecases.network.GetBookListUseCase
import com.andef.mybooks.presentation.main.ScreenToast
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FindScreenViewModel @Inject constructor(
    private val getBookListUseCase: GetBookListUseCase,
    private val getFavouriteBooksUseCase: GetFavouriteBooksUseCase,
    private val addFavouriteBookUseCase: AddFavouriteBookUseCase,
    private val removeFavouriteBookUseCase: RemoveFavouriteBookUseCase
) : ViewModel() {
    //состояние экрана поиска книг
    private val _state = MutableStateFlow(FindScreenState.Initial as FindScreenState)
    val state: StateFlow<FindScreenState> = _state

    //обработчик ошибок при обращении к серверу с книгами
    private val stateExceptionHandler = CoroutineExceptionHandler { _, e ->
        _state.value = FindScreenState.Error
        Log.d(TAG, e.toString())
    }

    //избранные книги
    val favouriteBooks = getFavouriteBooksUseCase.execute()

    //тосты при работе с базой данных избранных книг
    private val _toast = MutableStateFlow(ScreenToast.Initial as ScreenToast)
    val toasts: StateFlow<ScreenToast> = _toast

    //обработчик ошибок при добавлении книги в базу данных избранных книг
    private val toastsAddExceptionHandler = CoroutineExceptionHandler { _, e ->
        _toast.value = ScreenToast.ErrorAdd
        Log.d(TAG, e.toString())
    }

    //обработчик ошибок при удалении книги из базы данных избранных книг
    private val toastsRemoveExceptionHandler = CoroutineExceptionHandler { _, e ->
        _toast.value = ScreenToast.ErrorRemove
        Log.d(TAG, e.toString())
    }

    //текущая корутина
    private var currentJob: Job? = null

    //загрузка списка книг с сервера
    fun loadBookList(query: String, delay: Long = 0) {
        //отмена прошлой корутины (запроса), запуск новой
        currentJob?.cancel()
        currentJob = viewModelScope.launch(stateExceptionHandler) {
            delay(delay)
            _state.value = FindScreenState.Loading
            val bookList = withContext(Dispatchers.IO) {
                getBookListUseCase.execute(query)
            }
            _state.value = FindScreenState.BookList(bookList)
        }
    }

    //добавление или удаление книги из базы данных избранных книг
    //если в избранном - удаляем, если нет - добавляем
    fun addOrRemoveFavouriteBook(book: Book, isFavourite: Boolean) {
        if (!isFavourite) {
            viewModelScope.launch(toastsAddExceptionHandler) {
                withContext(Dispatchers.IO) {
                    addFavouriteBookUseCase.execute(book)
                }
                _toast.value = ScreenToast.SuccessAdd
            }
        } else {
            viewModelScope.launch(toastsRemoveExceptionHandler) {
                withContext(Dispatchers.IO) {
                    removeFavouriteBookUseCase.execute(book.id)
                }
                _toast.value = ScreenToast.SuccessRemove
            }
        }
    }

    //начальное значение toast
    fun getInitialToast() {
        _toast.value = ScreenToast.Initial
    }

    //показать начальный экран
    fun getInitialScreen() {
        currentJob?.cancel()
        _state.value = FindScreenState.Initial
    }

    companion object {
        private const val TAG = "FindScreenViewModel"
    }
}