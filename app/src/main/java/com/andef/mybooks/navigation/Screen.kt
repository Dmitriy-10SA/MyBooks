package com.andef.mybooks.navigation

sealed class Screen(val route: String) {
    data object FindScreen : Screen("find_screen")
    data object FavouriteScreen : Screen("favourite_screen")
}