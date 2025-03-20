package com.andef.mybooks.di.database

import com.andef.mybooks.data.database.repository.BookDatabaseRepositoryImpl
import com.andef.mybooks.di.ApplicationScope
import com.andef.mybooks.domain.repository.database.BookDatabaseRepository
import dagger.Binds
import dagger.Module

//подстановка настоящего репозитория для действия с базой данных с избранными книгами
@Module
interface BookDatabaseRepositoryModule {
    @ApplicationScope
    @Binds
    fun bindBookDatabaseRepository(impl: BookDatabaseRepositoryImpl): BookDatabaseRepository
}