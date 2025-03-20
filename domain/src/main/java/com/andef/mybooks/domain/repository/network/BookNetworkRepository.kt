package com.andef.mybooks.domain.repository.network

import com.andef.mybooks.domain.entities.Book

interface BookNetworkRepository {
    //получение книг по запросу (тексту, введенным пользователем)
    suspend fun getBookList(query: String): List<Book>
}