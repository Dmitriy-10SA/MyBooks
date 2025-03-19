package com.andef.mybooks.di

import com.andef.mybooks.data.repository.BookNetworkRepositoryImpl
import com.andef.mybooks.domain.repository.BookNetworkRepository
import dagger.Binds
import dagger.Module

@Module
interface BookNetworkRepositoryModule {
    @ApplicationScope
    @Binds
    fun bindBookNetworkRepository(impl: BookNetworkRepositoryImpl): BookNetworkRepository
}