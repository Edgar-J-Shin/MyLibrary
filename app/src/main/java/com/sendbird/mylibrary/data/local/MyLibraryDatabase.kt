package com.sendbird.mylibrary.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sendbird.mylibrary.data.local.dao.BookmarkDao
import com.sendbird.mylibrary.data.local.entity.BookEntity

@Database(
    entities = [(BookEntity::class)],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    value = []
)
abstract class MyLibraryDatabase : RoomDatabase() {

    abstract fun bookmarkDao(): BookmarkDao

    companion object {
        private const val DB_NAME = "MyLibrary.db"

        fun create(context: Context) = Room.databaseBuilder(context.applicationContext, MyLibraryDatabase::class.java, DB_NAME).build()
    }
}