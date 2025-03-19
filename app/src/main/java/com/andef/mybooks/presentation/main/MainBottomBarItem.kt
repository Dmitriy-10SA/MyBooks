package com.andef.mybooks.presentation.main

import com.andef.mybooks.R
import com.andef.mybooks.navigation.Screen

sealed class MainBottomBarItem(
    val titleResId: Int,
    val icon: Int,
    val contentDescriptionResId: Int,
    val route: String
) {
    data object FindScreenItem : MainBottomBarItem(
        titleResId = R.string.search,
        icon = R.drawable.magnifier,
        contentDescriptionResId = R.string.magnifying_glass_icon,
        route = Screen.FindScreen.route
    )

    data object FavouriteScreenItem : MainBottomBarItem(
        titleResId = R.string.favourite,
        icon = R.drawable.favourite,
        contentDescriptionResId = R.string.heart_icon,
        route = Screen.FavouriteScreen.route
    )
}