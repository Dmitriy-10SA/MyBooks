package com.andef.mybooks.data.network.dto

import com.google.gson.annotations.SerializedName

//список книг, полученных от сервера
//bookList - тот самый список :)
data class BookListDto(
    @SerializedName("items")
    val bookList: List<BookDto>?
)