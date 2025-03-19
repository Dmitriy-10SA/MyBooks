package com.andef.mybooks.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {
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

    private var instance: ApiService? = null

    fun getInstance(): ApiService {
        if (instance != null) {
            return instance!!
        }
        synchronized(this) {
            if (instance == null) {
                instance = retrofit.create(ApiService::class.java)
            }
            return instance!!
        }
    }
}