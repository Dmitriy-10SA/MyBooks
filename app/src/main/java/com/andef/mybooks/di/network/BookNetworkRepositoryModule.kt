package com.andef.mybooks.di.network

import com.andef.mybooks.data.network.repository.BookNetworkRepositoryImpl
import com.andef.mybooks.di.ApplicationScope
import com.andef.mybooks.domain.repository.network.BookNetworkRepository
import dagger.Binds
import dagger.Module

//подстановка настоящего репозитория для работой с сервером с книгами
@Module
interface BookNetworkRepositoryModule {
    @ApplicationScope
    @Binds
    fun bindBookNetworkRepository(impl: BookNetworkRepositoryImpl): BookNetworkRepository
}