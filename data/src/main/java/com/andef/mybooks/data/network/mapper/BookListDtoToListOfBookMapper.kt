package com.andef.mybooks.data.network.mapper

import com.andef.mybooks.data.network.dto.BookListDto
import com.andef.mybooks.domain.entities.Book

//преобразователь BookListDto в List<Book>

//ГЛАВНЫЙ МОМЕНТ: поле publishedDate берем не полностью, только год
//(т.е. подстроку из первых четырех цифр)
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
                        //взятие подстроки для выделения только года
                        publishedYear = book.info?.publishedDate?.substring(0..3),
                        description = book.info?.description,
                        thumbnailLink = book.info?.image?.link
                    )
                )
            }
        }
        return listOfBook.toList()
    }
}