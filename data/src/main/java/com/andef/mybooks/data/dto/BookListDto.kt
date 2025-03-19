package com.andef.mybooks.data.dto

import com.google.gson.annotations.SerializedName

data class BookListDto(
    @SerializedName("items")
    val bookList: List<BookDto>
)