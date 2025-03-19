package com.andef.mybooks.domain.usecases

import com.andef.mybooks.domain.entities.Book
import com.andef.mybooks.domain.repository.BookNetworkRepository
import javax.inject.Inject

class GetBooksUseCase @Inject constructor(
    private val repository: BookNetworkRepository
) {
    suspend fun execute(title: String): List<Book> {
        return repository.getBookList(title)
    }
}