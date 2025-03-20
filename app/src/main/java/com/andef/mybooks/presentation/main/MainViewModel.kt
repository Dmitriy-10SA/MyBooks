package com.andef.mybooks.presentation.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andef.mybooks.domain.entities.Book
import com.andef.mybooks.domain.usecases.database.AddFavouriteBookUseCase
import com.andef.mybooks.domain.usecases.database.GetFavouriteBooksUseCase
import com.andef.mybooks.domain.usecases.database.RemoveFavouriteBookUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getFavouriteBooksUseCase: GetFavouriteBooksUseCase,
    private val addFavouriteBookUseCase: AddFavouriteBookUseCase,
    private val removeFavouriteBookUseCase: RemoveFavouriteBookUseCase
) : ViewModel() {
    //избранные книги
    val favouriteBooks = getFavouriteBooksUseCase.execute()

    //тосты при работе с базой данных избранных книг
    private val _toast = MutableStateFlow(MainScreenToast.Initial as MainScreenToast)
    val toasts: StateFlow<MainScreenToast> = _toast

    //обработчик ошибок при добавлении книги в базу данных избранных книг
    private val toastsAddExceptionHandler = CoroutineExceptionHandler { _, e ->
        _toast.value = MainScreenToast.ErrorAdd
        Log.d(TAG, e.toString())
    }

    //обработчик ошибок при удалении книги из базы данных избранных книг
    private val toastsRemoveExceptionHandler = CoroutineExceptionHandler { _, e ->
        _toast.value = MainScreenToast.ErrorRemove
        Log.d(TAG, e.toString())
    }

    //добавление или удаление книги из базы данных избранных книг
    //если в избранном - удаляем, если нет - добавляем
    fun addOrRemoveFavouriteBook(book: Book, isFavourite: Boolean) {
        if (!isFavourite) {
            viewModelScope.launch(toastsAddExceptionHandler) {
                withContext(Dispatchers.IO) {
                    addFavouriteBookUseCase.execute(book)
                }
                _toast.value = MainScreenToast.SuccessAdd
            }
        } else {
            viewModelScope.launch(toastsRemoveExceptionHandler) {
                withContext(Dispatchers.IO) {
                    removeFavouriteBookUseCase.execute(book.id)
                }
                _toast.value = MainScreenToast.SuccessRemove
            }
        }
    }

    //начальное значение toast
    fun getInitialToast() {
        _toast.value = MainScreenToast.Initial
    }

    companion object {
        private const val TAG = "MainViewModel"
    }
}