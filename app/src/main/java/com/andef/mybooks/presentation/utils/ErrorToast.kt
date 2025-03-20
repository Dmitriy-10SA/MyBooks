package com.andef.mybooks.presentation.utils

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.MutableState
import com.andef.mybooks.presentation.main.MainSnackBarType
import kotlinx.coroutines.CoroutineScope

fun errorToast(
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope,
    snackbarType: MutableState<MainSnackBarType>,
    text: String
) {
    snackbarType.value = MainSnackBarType.ERROR
    showToast(
        snackbarHostState = snackbarHostState,
        scope = scope,
        text = text
    )
}