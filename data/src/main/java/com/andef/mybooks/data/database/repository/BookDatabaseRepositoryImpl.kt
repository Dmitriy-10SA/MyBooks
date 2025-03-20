package com.andef.mybooks.data.database.repository

import com.andef.mybooks.data.database.datasource.BooksDao
import com.andef.mybooks.data.database.mapper.BookToFavouriteBookDbModelMapper
import com.andef.mybooks.data.database.mapper.BooksDbModelListToBookSetMapper
import com.andef.mybooks.domain.entities.Book
import com.andef.mybooks.domain.repository.database.BookDatabaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BookDatabaseRepositoryImpl @Inject constructor(
    private val booksDao: BooksDao
) : BookDatabaseRepository {
    override suspend fun addFavouriteBook(book: Book) {
        booksDao.addBook(BookToFavouriteBookDbModelMapper.map(book))
    }

    override suspend fun removeFavouriteBook(id: String) {
        booksDao.removeBook(id)
    }

    override fun getFavouriteBooks(): Flow<Set<Book>> {
        return booksDao.getFavouriteBooks().map {
            BooksDbModelListToBookSetMapper.map(it)
        }
    }
}