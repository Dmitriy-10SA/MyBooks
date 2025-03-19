package com.andef.mybooks.di

import com.andef.mybooks.data.api.ApiFactory
import com.andef.mybooks.data.api.ApiService
import dagger.Module
import dagger.Provides

@Module
class ApiServiceModule {
    @ApplicationScope
    @Provides
    fun provideApiService(): ApiService = ApiFactory.getInstance()
}