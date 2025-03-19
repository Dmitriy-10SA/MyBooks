package com.andef.mybooks.domain.repository

import com.andef.mybooks.domain.entities.Book

interface BookNetworkRepository {
    suspend fun getBookList(title: String): List<Book>
}