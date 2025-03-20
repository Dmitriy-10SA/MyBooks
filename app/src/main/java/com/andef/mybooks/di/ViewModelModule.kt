package com.andef.mybooks.di

import androidx.lifecycle.ViewModel
import com.andef.mybooks.presentation.favourite.FavouriteScreenViewModel
import com.andef.mybooks.presentation.find.FindScreenViewModel
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
    @ViewModelKey(FavouriteScreenViewModel::class)
    fun bindFavouriteScreenViewModel(impl: FavouriteScreenViewModel): ViewModel
}