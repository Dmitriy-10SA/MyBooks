package com.andef.mybooks.presentation.main

//все тосты для экрана поиск книг
sealed class ScreenToast {
    //начальное состояние (без тоста)
    data object Initial : ScreenToast()
    //успешное добавление
    data object SuccessAdd : ScreenToast()
    //успешное удаление
    data object SuccessRemove : ScreenToast()
    //добавление с ошибкой
    data object ErrorAdd : ScreenToast()
    //удаление с ошибкой
    data object ErrorRemove : ScreenToast()
}