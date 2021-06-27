package com.sendbird.mylibrary.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.sendbird.mylibrary.data.local.BaseDao
import com.sendbird.mylibrary.data.local.entity.BookEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface BookmarkDao : BaseDao<BookEntity> {

    @Query("SELECT * FROM books")
    fun select(): Single<List<BookEntity>>

    @Query("SELECT * FROM books WHERE :isbn13 == isbn13")
    fun selectByIsbn13(isbn13: String): Single<List<BookEntity>>

    @Query("SELECT * FROM books ORDER BY CASE WHEN :isAsc = 1 THEN price END ASC, CASE WHEN :isAsc = 0 THEN price END DESC")
    fun selectByPrice(isAsc: Boolean): Single<List<BookEntity>>

    @Query("SELECT * FROM books ORDER BY CASE WHEN :isAsc = 1 THEN title END ASC, CASE WHEN :isAsc = 0 THEN title END DESC")
    fun selectByAlphabet(isAsc: Boolean): Single<List<BookEntity>>

    @Query("DELETE FROM books WHERE :isbn13 == isbn13")
    fun deleteByIsbn13(isbn13: String): Completable
}