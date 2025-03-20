package com.andef.mybooks.domain.usecases.database

import com.andef.mybooks.domain.entities.Book
import com.andef.mybooks.domain.repository.database.BookDatabaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavouriteBooksUseCase @Inject constructor(
    private val repository: BookDatabaseRepository
) {
    fun execute(): Flow<Set<Book>> {
        return repository.getFavouriteBooks()
    }
}