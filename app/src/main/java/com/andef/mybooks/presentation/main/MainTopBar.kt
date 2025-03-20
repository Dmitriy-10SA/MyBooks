package com.andef.mybooks.presentation.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.andef.mybooks.R

//top bar для для экрана со списком избранных книг
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(onBackButtonClickListener: () -> Unit) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(R.color.white),
            navigationIconContentColor = colorResource(R.color.black)
        ),
        title = {
            Text(
                text = stringResource(R.string.favourite),
                fontSize = 18.sp,
                color = colorResource(R.color.black)
            )
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