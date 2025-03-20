package com.andef.mybooks.presentation.find

import com.andef.mybooks.domain.entities.Book

//различные состояния для экрана поиска книг
sealed class FindScreenState {
    //начальное состояние (пустой ввод или никаких действий еще не было)
    data object Initial : FindScreenState()

    //загрузка книг по запросу
    data object Loading : FindScreenState()

    //ошибка во время запроса
    data object Error : FindScreenState()

    //успешно получен список книг
    data class BookList(val bookList: List<Book>) : FindScreenState()
}