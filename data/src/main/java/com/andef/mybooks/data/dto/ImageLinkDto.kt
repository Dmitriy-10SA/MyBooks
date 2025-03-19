package com.andef.mybooks.data.dto

import com.google.gson.annotations.SerializedName

data class ImageLinkDto(
    @SerializedName("thumbnail")
    val link: String?
)