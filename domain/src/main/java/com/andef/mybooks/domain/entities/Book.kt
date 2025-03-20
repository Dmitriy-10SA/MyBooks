package com.andef.mybooks.domain.entities

/*класс книга
authors - авторы
title - название
publishedYear - год выпуска
description - описание
thumbnailLink - ссылка на изображение обложки
*/
data class Book(
    val id: String,
    val authors: List<String>?,
    val title: String?,
    val publishedYear: String?,
    val description: String?,
    val thumbnailLink: String?
)
