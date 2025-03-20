package com.andef.mybooks.presentation.book

import com.andef.mybooks.domain.entities.Book

//состояние экрана с информацией о книге
sealed class BookScreenState {
    //начальное состояние
    data object Initial : BookScreenState()

    //ожидание получения книги по id из базы данных избранных книг
    data object Loading : BookScreenState()

    //ошибка при получении книги по id из базы данных избранных книг
    data object Error : BookScreenState()

    //успешное получение книги из базы данных избранных книг
    data class BookLoad(val book: Book) : BookScreenState()
}