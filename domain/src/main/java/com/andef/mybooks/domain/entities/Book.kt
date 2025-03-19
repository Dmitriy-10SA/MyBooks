package com.andef.mybooks.domain.entities

data class Book(
    val id: String,
    val authors: List<String>?,
    val title: String?,
    val publishedYear: String?,
    val description: String?,
    val thumbnailLink: String?
)
