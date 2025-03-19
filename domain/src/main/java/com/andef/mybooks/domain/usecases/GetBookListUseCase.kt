package com.andef.mybooks.domain.usecases

import com.andef.mybooks.domain.entities.Book
import com.andef.mybooks.domain.repository.BookNetworkRepository
import javax.inject.Inject

class GetBookListUseCase @Inject constructor(
    private val repository: BookNetworkRepository
) {
    suspend fun execute(query: String): List<Book> {
        return repository.getBookList(query)
    }
}