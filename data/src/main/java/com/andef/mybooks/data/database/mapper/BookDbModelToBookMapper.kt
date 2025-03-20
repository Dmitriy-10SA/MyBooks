package com.andef.mybooks.data.database.mapper

import com.andef.mybooks.data.database.dbmodel.BookDbModel
import com.andef.mybooks.domain.entities.Book

internal object BookDbModelToBookMapper {
    fun map(bookDbModel: BookDbModel): Book {
        return Book(
            id = bookDbModel.id,
            authors = bookDbModel.authors,
            title = bookDbModel.title,
            publishedYear = bookDbModel.publishedYear,
            description = bookDbModel.description,
            thumbnailLink = bookDbModel.thumbnailLink
        )
    }
}