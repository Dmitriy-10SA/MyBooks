package com.andef.mybooks.data.dto

import com.google.gson.annotations.SerializedName

data class BookInfoDto(
    @SerializedName("authors")
    val authors: List<String>?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("publishedDate")
    val publishedDate: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("imageLinks")
    val image: ImageLinkDto?
)