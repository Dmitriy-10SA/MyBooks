package com.andef.mybooks.presentation.book

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.andef.mybooks.R

//top bar для экрана с подробной информацией о книге
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookTopBar(onBackButtonClickListener: () -> Unit) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(R.color.white),
            navigationIconContentColor = colorResource(R.color.black)
        ),
        title = {},
        actions = {

        },
        navigationIcon = {
            IconButton(
                onClick = {
                    onBackButtonClickListener()
                }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.back)
                )
            }
        }
    )
}