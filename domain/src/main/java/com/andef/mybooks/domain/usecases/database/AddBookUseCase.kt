package com.andef.mybooks.domain.usecases.database

import com.andef.mybooks.domain.entities.Book
import com.andef.mybooks.domain.repository.database.BookDatabaseRepository
import javax.inject.Inject

class AddBookUseCase @Inject constructor(
    private val repository: BookDatabaseRepository
) {
    suspend fun execute(book: Book) {
        repository.addBook(book)
    }
}