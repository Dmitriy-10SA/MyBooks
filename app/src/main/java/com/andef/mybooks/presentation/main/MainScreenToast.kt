package com.andef.mybooks.presentation.main

//все тосты для действий с базой данных избранных книг
sealed class MainScreenToast {
    //начальное состояние (без тоста)
    data object Initial : MainScreenToast()

    //успешное добавление
    data object SuccessAdd : MainScreenToast()

    //успешное удаление
    data object SuccessRemove : MainScreenToast()

    //добавление с ошибкой
    data object ErrorAdd : MainScreenToast()

    //удаление с ошибкой
    data object ErrorRemove : MainScreenToast()
}