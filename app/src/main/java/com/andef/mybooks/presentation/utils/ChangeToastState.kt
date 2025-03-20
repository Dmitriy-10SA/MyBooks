package com.andef.mybooks.presentation.utils

import android.util.Log
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.ui.res.stringResource
import com.andef.mybooks.R
import com.andef.mybooks.presentation.main.MainSnackBarType
import com.andef.mybooks.presentation.main.MainScreenToast
import kotlinx.coroutines.CoroutineScope

//изменение состояния toast (успех, ошибка или ничего)
@Composable
fun ChangeToastState(
    toasts: State<MainScreenToast>,
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope,
    snackbarType: MutableState<MainSnackBarType>,
    initialToastAction: () -> Unit
) {
    when (toasts.value) {
        MainScreenToast.ErrorAdd -> {
            Log.d("LOG_LOG", toasts.value.toString())
            errorToast(
                snackbarHostState = snackbarHostState,
                scope = scope,
                snackbarType = snackbarType,
                text = stringResource(R.string.error_add_favourite_book)
            )
            initialToastAction()
        }

        MainScreenToast.ErrorRemove -> {
            Log.d("LOG_LOG", toasts.value.toString())
            errorToast(
                snackbarHostState = snackbarHostState,
                scope = scope,
                snackbarType = snackbarType,
                text = stringResource(R.string.error_remove_favourite_book)
            )
            initialToastAction()
        }

        MainScreenToast.Initial -> {
            Log.d("LOG_LOG", toasts.value.toString())
            initialToastAction()
        }

        MainScreenToast.SuccessAdd -> {
            Log.d("LOG_LOG", toasts.value.toString())
            successToast(
                snackbarHostState = snackbarHostState,
                scope = scope,
                snackbarType = snackbarType,
                text = stringResource(R.string.success_add_favourite_book)
            )
            initialToastAction()
        }

        MainScreenToast.SuccessRemove -> {
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