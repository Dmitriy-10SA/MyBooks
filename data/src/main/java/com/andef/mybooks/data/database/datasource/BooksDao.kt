package com.andef.mybooks.data.database.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.andef.mybooks.data.database.dbmodel.BookDbModel
import kotlinx.coroutines.flow.Flow

@Dao
interface BooksDao {
    //добавление книги в базу данных избранных книг
    @Insert
    suspend fun addBook(book: BookDbModel)

    //удаление книги из базы данных избранных книг
    @Query("DELETE FROM book WHERE id = :id")
    suspend fun removeBook(id: String)

    //получение избранных книг из базы данных
    @Query("SELECT * FROM book")
    fun getFavouriteBooks(): Flow<List<BookDbModel>>
}