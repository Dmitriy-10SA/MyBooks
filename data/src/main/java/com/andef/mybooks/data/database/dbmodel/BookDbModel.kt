package com.andef.mybooks.data.database.dbmodel

import androidx.room.Entity
import androidx.room.PrimaryKey

/*сущность книги, для хранения в базе данных избранных книг
authors - авторы
title - название
publishedYear - год выпуска
description - описание
thumbnailLink - ссылка на изображение обложки
*/
@Entity("book")
data class BookDbModel(
    @PrimaryKey
    val id: String,
    val authors: List<String>?,
    val title: String?,
    val publishedYear: String?,
    val description: String?,
    val thumbnailLink: String?
)
