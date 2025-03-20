package com.andef.mybooks.domain.usecases.database

import com.andef.mybooks.domain.repository.database.BookDatabaseRepository
import javax.inject.Inject

class RemoveFavouriteBookUseCase @Inject constructor(
    private val repository: BookDatabaseRepository
) {
    suspend fun execute(id: String) {
        repository.removeFavouriteBook(id)
    }
}