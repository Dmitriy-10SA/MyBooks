package com.andef.mybooks.data.database.mapper

import com.andef.mybooks.data.database.dbmodel.BookDbModel
import com.andef.mybooks.domain.entities.Book

//преобразователь List<BookDbModel> в Set<Book>
//Set<Book> для более быстрого поиска книги, которая в базе данных избранных книг
internal object BooksDbModelListToBookSetMapper {
    private fun additionalMap(bookDbModel: BookDbModel): Book {
        return Book(
            id = bookDbModel.id,
            authors = bookDbModel.authors,
            title = bookDbModel.title,
            publishedYear = bookDbModel.publishedYear,
            description = bookDbModel.description,
            thumbnailLink = bookDbModel.thumbnailLink
        )
    }

    fun map(booksDbModel: List<BookDbModel>): Set<Book> {
        return booksDbModel.map {
            additionalMap(it)
        }.toSet()
    }
}