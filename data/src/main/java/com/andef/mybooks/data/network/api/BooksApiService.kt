package com.andef.mybooks.data.network.api

import com.andef.mybooks.data.network.dto.BookListDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BooksApiService {
    //получение книг по запросу (тексту, введенным пользователем)
    @GET(VOLUMES)
    suspend fun getBookList(@Query("q") query: String): Response<BookListDto>

    companion object {
        private const val VOLUMES = "volumes"
    }
}