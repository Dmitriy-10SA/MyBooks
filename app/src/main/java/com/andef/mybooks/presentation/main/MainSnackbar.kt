package com.andef.mybooks.presentation.main

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.andef.mybooks.R

//toast при работе с базой данных избранных книг
@Composable
fun MainSnackbar(
    snackbarHostState: SnackbarHostState,
    snackbarType: MainSnackBarType
) {
    SnackbarHost(
        hostState = snackbarHostState,
        snackbar = {
            Snackbar(
                snackbarData = it,
                containerColor = when (snackbarType) {
                    MainSnackBarType.ERROR -> colorResource(R.color.red)
                    MainSnackBarType.SUCCESS -> colorResource(R.color.blue)
                },
                contentColor = colorResource(R.color.white),
                actionColor = colorResource(R.color.white),
                actionContentColor = colorResource(R.color.white),
                dismissActionContentColor = colorResource(R.color.white),
                shape = RoundedCornerShape(12.dp)
            )
        }
    )
}