package com.andef.mybooks.presentation.utils

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun showToast(
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope,
    text: String
) {
    snackbarHostState.currentSnackbarData?.dismiss()
    scope.launch {
        snackbarHostState.showSnackbar(
            message = text,
            duration = SnackbarDuration.Short,
            withDismissAction = true
        )
    }
}