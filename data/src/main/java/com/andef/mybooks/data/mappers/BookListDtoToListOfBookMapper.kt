package com.andef.mybooks.data.mappers

import com.andef.mybooks.data.dto.BookListDto
import com.andef.mybooks.domain.entities.Book

internal object BookListDtoToListOfBookMapper {
    fun map(bookListDto: BookListDto?): List<Book> {
        val listOfBook = mutableListOf<Book>()

        if (bookListDto?.bookList != null) {
            for (book in bookListDto.bookList) {
                listOfBook.add(
                    Book(
                        id = book.id,
                        authors = book.info?.authors,
                        title = book.info?.title,
                        publishedYear = book.info?.publishedDate,
                        description = book.info?.description,
                        thumbnailLink = book.info?.image?.link
                    )
                )
            }
        }
        return listOfBook.toList()
    }
}