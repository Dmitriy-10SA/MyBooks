package com.andef.mybooks

import android.app.Application
import com.andef.mybooks.di.DaggerAppComponent

class MyBooksApplication : Application() {
    val component by lazy {
        DaggerAppComponent.create()
    }
}