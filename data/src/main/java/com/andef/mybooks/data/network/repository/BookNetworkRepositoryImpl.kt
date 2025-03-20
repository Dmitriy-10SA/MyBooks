package com.andef.mybooks.data.network.repository

import com.andef.mybooks.data.network.api.BooksApiService
import com.andef.mybooks.data.network.mapper.BookListDtoToListOfBookMapper
import com.andef.mybooks.domain.entities.Book
import com.andef.mybooks.domain.repository.network.BookNetworkRepository
import okio.IOException
import javax.inject.Inject

class BookNetworkRepositoryImpl @Inject constructor(
    private val booksApiService: BooksApiService
) : BookNetworkRepository {
    override suspend fun getBookList(query: String): List<Book> {
        val response = booksApiService.getBookList(query)
        if (response.isSuccessful) {
            return BookListDtoToListOfBookMapper.map(response.body())
        } else {
            throw IOException("Error loading the book list")
        }
    }
}