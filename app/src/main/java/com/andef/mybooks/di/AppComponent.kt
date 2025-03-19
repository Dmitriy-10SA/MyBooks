package com.andef.mybooks.di

import com.andef.mybooks.presentation.MainActivity
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        ApiServiceModule::class,
        BookNetworkRepositoryModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}