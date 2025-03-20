package com.andef.mybooks.presentation.main

import com.andef.mybooks.R
import com.andef.mybooks.navigation.Screen

//элементы нижней навигации приложения
sealed class MainBottomBarItem(
    val titleResId: Int,
    val icon: Int,
    val contentDescriptionResId: Int,
    val route: String
) {
    //элемент навигации "поиск"
    data object FindScreenItem : MainBottomBarItem(
        titleResId = R.string.search,
        icon = R.drawable.magnifier,
        contentDescriptionResId = R.string.magnifying_glass_icon,
        route = Screen.FindScreen.route
    )

    //элемент навигации "избранное"
    data object FavouriteScreenItem : MainBottomBarItem(
        titleResId = R.string.favourite,
        icon = R.drawable.favourite,
        contentDescriptionResId = R.string.heart_icon,
        route = Screen.FavouriteScreen.route
    )
}