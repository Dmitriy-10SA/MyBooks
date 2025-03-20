package com.andef.mybooks.presentation.utils

import android.util.Log
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.ui.res.stringResource
import com.andef.mybooks.R
import com.andef.mybooks.presentation.main.ScreenToast
import com.andef.mybooks.presentation.main.MainSnackBarType
import kotlinx.coroutines.CoroutineScope

@Composable
fun ChangeToastState(
    toasts: State<ScreenToast>,
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope,
    snackbarType: MutableState<MainSnackBarType>,
    initialToastAction: () -> Unit
) {
    when (toasts.value) {
        ScreenToast.ErrorAdd -> {
            Log.d("LOG_LOG", toasts.value.toString())
            errorToast(
                snackbarHostState = snackbarHostState,
                scope = scope,
                snackbarType = snackbarType,
                text = stringResource(R.string.error_add_favourite_book)
            )
            initialToastAction()
        }

        ScreenToast.ErrorRemove -> {
            Log.d("LOG_LOG", toasts.value.toString())
            errorToast(
                snackbarHostState = snackbarHostState,
                scope = scope,
                snackbarType = snackbarType,
                text = stringResource(R.string.error_remove_favourite_book)
            )
            initialToastAction()
        }

        ScreenToast.Initial -> {
            Log.d("LOG_LOG", toasts.value.toString())
            initialToastAction()
        }

        ScreenToast.SuccessAdd -> {
            Log.d("LOG_LOG", toasts.value.toString())
            successToast(
                snackbarHostState = snackbarHostState,
                scope = scope,
                snackbarType = snackbarType,
                text = stringResource(R.string.success_add_favourite_book)
            )
            initialToastAction()
        }

        ScreenToast.SuccessRemove -> {
            Log.d("LOG_LOG", toasts.value.toString())
            successToast(
                snackbarHostState = snackbarHostState,
                scope = scope,
                snackbarType = snackbarType,
                text = stringResource(R.string.success_remove_favourite_book)
            )
            initialToastAction()
        }
    }
}