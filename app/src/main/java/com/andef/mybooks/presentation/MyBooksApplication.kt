package com.andef.mybooks.presentation

import android.app.Application
import com.andef.mybooks.di.DaggerAppComponent

class MyBooksApplication : Application() {
    val component by lazy {
        DaggerAppComponent.factory().create(this)
    }
}