package com.andef.mybooks.data.network.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BooksApiFactory {
    private const val BASE_URL = "https://www.googleapis.com/books/v1/"

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    private var instance: BooksApiService? = null

    fun getInstance(): BooksApiService {
        if (instance != null) {
            return instance!!
        }
        synchronized(this) {
            if (instance == null) {
                instance = retrofit.create(BooksApiService::class.java)
            }
            return instance!!
        }
    }
}