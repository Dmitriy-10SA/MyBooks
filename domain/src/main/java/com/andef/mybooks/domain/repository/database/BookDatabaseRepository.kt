package com.andef.mybooks.domain.repository.database

import com.andef.mybooks.domain.entities.Book
import kotlinx.coroutines.flow.Flow

interface BookDatabaseRepository {
    suspend fun addBook(book: Book)
    suspend fun removeBook(id: String)
    fun getFavouriteBooks(): Flow<Set<Book>>
}