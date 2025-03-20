package com.andef.mybooks.di

import androidx.lifecycle.ViewModel
import com.andef.mybooks.presentation.book.BookScreenViewModel
import com.andef.mybooks.presentation.find.FindScreenViewModel
import com.andef.mybooks.presentation.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @IntoMap
    @Binds
    @ViewModelKey(FindScreenViewModel::class)
    fun bindFindScreenViewModel(impl: FindScreenViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(BookScreenViewModel::class)
    fun bindBookScreenViewModel(impl: BookScreenViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(impl: MainViewModel): ViewModel
}