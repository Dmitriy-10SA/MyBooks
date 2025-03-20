package com.andef.mybooks.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.andef.mybooks.presentation.main.MainScreen
import com.andef.mybooks.ui.theme.MyBooksTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    private val component by lazy {
        (application as MyBooksApplication).component
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyBooksTheme(darkTheme = false) {
                MainScreen(viewModelFactory = viewModelFactory)
            }
        }
    }
}