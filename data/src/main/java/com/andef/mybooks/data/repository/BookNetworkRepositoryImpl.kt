package com.andef.mybooks.data.repository

import com.andef.mybooks.data.api.ApiService
import com.andef.mybooks.data.mappers.BookListDtoToListOfBookMapper
import com.andef.mybooks.domain.entities.Book
import com.andef.mybooks.domain.repository.BookNetworkRepository
import javax.inject.Inject

class BookNetworkRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : BookNetworkRepository {
    override suspend fun getBookList(title: String): List<Book> {
        return BookListDtoToListOfBookMapper.map(apiService.getBookList(title))
    }
}