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
    private val _state = MutableStateFlow(FindScreenState.Initial as FindScreenState)
    val state: StateFlow<FindScreenState> = _state

    private val exceptionHandler = CoroutineExceptionHandler { _, e ->
        _state.value = FindScreenState.Error
        Log.d(TAG, e.toString())
    }

    private var currentJob: Job? = null

    fun loadBookList(query: String, delay: Long = 0) {
        currentJob?.cancel()
        currentJob = viewModelScope.launch(exceptionHandler) {
            _state.value = FindScreenState.Loading
            delay(delay)
            val bookList = withContext(Dispatchers.IO) {
                getBookListUseCase.execute(query)
            }
            _state.value = FindScreenState.BookList(bookList)
        }
    }

    fun getInitialScreen() {
        currentJob?.cancel()
        _state.value = FindScreenState.Initial
    }

    companion object {
        private const val TAG = "FindScreenViewModel"
    }
}