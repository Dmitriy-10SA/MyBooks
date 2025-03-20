package com.andef.mybooks.di

import android.app.Application
import com.andef.mybooks.di.database.BooksDaoModule
import com.andef.mybooks.di.network.BookNetworkRepositoryModule
import com.andef.mybooks.di.network.BooksApiServiceModule
import com.andef.mybooks.presentation.MainActivity
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        BooksApiServiceModule::class,
        BookNetworkRepositoryModule::class,
        ViewModelModule::class,
        BooksDaoModule::class,
        BooksApiServiceModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)

    @Component.Factory
    interface AppComponentFactory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}