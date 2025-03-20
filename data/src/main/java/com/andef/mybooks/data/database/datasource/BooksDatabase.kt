package com.andef.mybooks.data.database.datasource

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.andef.mybooks.data.database.converter.StringListConverter
import com.andef.mybooks.data.database.dbmodel.BookDbModel

//база данных для хранения избранных книг
@Database(entities = [BookDbModel::class], version = 1, exportSchema = false)
@TypeConverters(StringListConverter::class)
abstract class BooksDatabase : RoomDatabase() {
    abstract val booksDao: BooksDao

    companion object {
        private const val DB_NAME = "favouriteBooks.db"
        private var instance: BooksDatabase? = null

        fun getInstance(application: Application): BooksDatabase {
            if (instance != null) {
                return instance!!
            }
            synchronized(this) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        application,
                        BooksDatabase::class.java,
                        DB_NAME
                    ).build()
                }
                return instance!!
            }
        }
    }
}