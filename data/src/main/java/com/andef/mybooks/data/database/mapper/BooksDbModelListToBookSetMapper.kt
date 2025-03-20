package com.andef.mybooks.data.database.mapper

import com.andef.mybooks.data.database.dbmodel.BookDbModel
import com.andef.mybooks.domain.entities.Book

//преобразователь List<BookDbModel> в Set<Book>
//Set<Book> для более быстрого поиска книги, которая в базе данных избранных книг
internal object BooksDbModelListToBookSetMapper {
    fun map(booksDbModel: List<BookDbModel>): Set<Book> {
        return booksDbModel.map {
            BookDbModelToBookMapper.map(it)
        }.toSet()
    }
}