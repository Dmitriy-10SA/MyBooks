package com.andef.mybooks.domain.repository.database

import com.andef.mybooks.domain.entities.Book
import kotlinx.coroutines.flow.Flow

interface BookDatabaseRepository {
    //добавление книги в базу данных избранных книг
    suspend fun addFavouriteBook(book: Book)

    //удаление книги из базы данных избранных книг
    suspend fun removeFavouriteBook(id: String)

    //получение избранных книг из базы данных
    //Set<Book> для быстрого поиска книги в базе данных избранных книг
    fun getFavouriteBooks(): Flow<Set<Book>>
}