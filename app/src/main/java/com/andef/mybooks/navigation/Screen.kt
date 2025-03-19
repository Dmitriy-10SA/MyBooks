package com.andef.mybooks.navigation

sealed class Screen(val route: String) {
    data object FindScreen : Screen(FIND_SCREEN_ROUTE)
    data object FindScreenBookList : Screen(FIND_SCREEN_BOOK_LIST_ROUTE)
    data object FindScreenBook : Screen(FIND_SCREEN_BOOK_ROUTE)
    data object FavouriteScreen : Screen(FAVOURITE_SCREEN_ROUTE)

    companion object {
        private const val FIND_SCREEN_ROUTE = "find_screen"
        private const val FIND_SCREEN_BOOK_LIST_ROUTE = "find_screen_book_list"
        private const val FIND_SCREEN_BOOK_ROUTE = "find_screen_book"
        private const val FAVOURITE_SCREEN_ROUTE = "favourite_screen"
    }
}