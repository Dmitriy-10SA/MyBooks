package com.andef.mybooks.di.database

import android.app.Application
import com.andef.mybooks.data.database.datasource.BooksDao
import com.andef.mybooks.data.database.datasource.BooksDatabase
import com.andef.mybooks.di.ApplicationScope
import dagger.Module
import dagger.Provides

//подстановка базы данных с избранными книгами
@Module
class BooksDaoModule {
    @ApplicationScope
    @Provides
    fun provideBooksDao(application: Application): BooksDao {
        return BooksDatabase.getInstance(application).booksDao
    }
}