package com.andef.mybooks.data.api

import com.andef.mybooks.data.dto.BookListDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(VOLUMES)
    suspend fun getBookList(@Query("q") query: String): Response<BookListDto>

    companion object {
        private const val VOLUMES = "volumes"
    }
}