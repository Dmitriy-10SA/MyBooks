package com.andef.mybooks.data.dto

import com.google.gson.annotations.SerializedName

data class BookDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("volumeInfo")
    val info: BookInfoDto?
)
