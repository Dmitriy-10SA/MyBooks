package com.andef.mybooks.data.network.dto

import com.google.gson.annotations.SerializedName

//Книга, полученная из запроса к серверу
//info - информация о книге
data class BookDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("volumeInfo")
    val info: BookInfoDto?
)
