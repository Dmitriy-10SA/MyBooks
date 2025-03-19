package com.andef.mybooks.di

import com.andef.mybooks.MainActivity
import dagger.Component

@ApplicationScope
@Component(modules = [ApiServiceModule::class, BookNetworkRepositoryModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}