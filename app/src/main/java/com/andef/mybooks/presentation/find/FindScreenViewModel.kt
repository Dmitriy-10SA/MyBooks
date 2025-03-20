package com.andef.mybooks.presentation.find

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andef.mybooks.domain.usecases.network.GetBookListUseCase
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
    private val getBookListUseCase: GetBookListUseCase
) : ViewModel() {
    //состояние экрана поиска книг
    private val _state = MutableStateFlow(FindScreenState.Initial as FindScreenState)
    val state: StateFlow<FindScreenState> = _state

    //обработчик ошибок при обращении к серверу с книгами
    private val stateExceptionHandler = CoroutineExceptionHandler { _, e ->
        _state.value = FindScreenState.Error
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

    //показать начальный экран
    fun getInitialScreen() {
        currentJob?.cancel()
        _state.value = FindScreenState.Initial
    }

    companion object {
        private const val TAG = "FindScreenViewModel"
    }
}