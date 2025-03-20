package com.andef.mybooks.data.network.dto

import com.google.gson.annotations.SerializedName

/*информация о книге
authors - авторы
title - название
publishedDate - дата публикации
description - описание
image - поле для хранения ссылки на обложку книги
 */
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