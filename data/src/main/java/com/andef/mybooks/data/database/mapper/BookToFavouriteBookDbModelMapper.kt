package com.andef.mybooks.data.database.mapper

import com.andef.mybooks.data.database.dbmodel.BookDbModel
import com.andef.mybooks.domain.entities.Book

//преобразователь Book в BookDbModel
internal object BookToFavouriteBookDbModelMapper {
    fun map(book: Book): BookDbModel {
        return BookDbModel(
            id = book.id,
            authors = book.authors,
            title = book.title,
            publishedYear = book.publishedYear,
            description = book.description,
            thumbnailLink = book.thumbnailLink
        )
    }
}