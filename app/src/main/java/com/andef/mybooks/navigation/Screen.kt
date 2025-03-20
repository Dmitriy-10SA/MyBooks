package com.andef.mybooks.navigation

//экраны приложения
sealed class Screen(val route: String) {
    //элемент навигации "поиск" (главный экран)
    data object FindScreen : Screen(FIND_SCREEN_ROUTE)

    //экран поиска книг (вложен в FindScreen)
    data object FindScreenBookList : Screen(FIND_SCREEN_BOOK_LIST_ROUTE)

    //экран с информацией о книге (вложен в FindScreen)
    data object FindScreenBook : Screen(FIND_SCREEN_BOOK_ROUTE)

    //элемент навигации "избранное", экран с избранными фильмами
    data object FavouriteScreen : Screen(FAVOURITE_SCREEN_ROUTE)

    companion object {
        private const val FIND_SCREEN_ROUTE = "find_screen"
        private const val FIND_SCREEN_BOOK_LIST_ROUTE = "find_screen_book_list"
        private const val FIND_SCREEN_BOOK_ROUTE = "find_screen_book"
        private const val FAVOURITE_SCREEN_ROUTE = "favourite_screen"
    }
}