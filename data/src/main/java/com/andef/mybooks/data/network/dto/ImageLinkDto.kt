package com.andef.mybooks.data.network.dto

import com.google.gson.annotations.SerializedName

//хранитель обложки книги
//link - ссылка на обложку книги
data class ImageLinkDto(
    @SerializedName("thumbnail")
    val link: String?
)