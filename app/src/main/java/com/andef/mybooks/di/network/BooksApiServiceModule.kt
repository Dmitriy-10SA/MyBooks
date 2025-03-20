package com.andef.mybooks.di.network

import com.andef.mybooks.data.network.api.BooksApiFactory
import com.andef.mybooks.data.network.api.BooksApiService
import com.andef.mybooks.di.ApplicationScope
import dagger.Module
import dagger.Provides

//подстановка BooksApiService для выполнения запросов к серверу с книгами
@Module
class BooksApiServiceModule {
    @ApplicationScope
    @Provides
    fun provideBooksApiService(): BooksApiService = BooksApiFactory.getInstance()
}