package com.andef.mybooks.data.repository

import com.andef.mybooks.data.api.ApiService
import com.andef.mybooks.data.mappers.BookListDtoToListOfBookMapper
import com.andef.mybooks.domain.entities.Book
import com.andef.mybooks.domain.repository.network.BookNetworkRepository
import okio.IOException
import javax.inject.Inject

class BookNetworkRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : BookNetworkRepository {
    override suspend fun getBookList(query: String): List<Book> {
        val response = apiService.getBookList(query)
        if (response.isSuccessful) {
            return BookListDtoToListOfBookMapper.map(response.body())
        } else {
            throw IOException("Error loading the book list")
        }
    }
}