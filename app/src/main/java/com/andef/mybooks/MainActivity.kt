package com.andef.mybooks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.andef.mybooks.presentation.MainScreen
import com.andef.mybooks.ui.theme.MyBooksTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    private val component by lazy {
        (application as MyBooksApplication).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyBooksTheme {
                MainScreen()
            }
        }
    }
}