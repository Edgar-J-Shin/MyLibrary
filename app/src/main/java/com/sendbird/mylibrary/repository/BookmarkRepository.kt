package com.sendbird.mylibrary.repository

import com.sendbird.mylibrary.data.local.entity.BookEntity
import io.reactivex.Completable
import io.reactivex.Single

interface BookmarkRepository {

    fun getBookmarkListBySort(filterType: FILTER_TYPE): Single<List<BookEntity>>

    fun getBookmarkByIsbn13(isbn13: String): Single<List<BookEntity>>

    fun insertBookmark(bookEntity: BookEntity): Single<Long>

    fun deleteBookmark(bookEntity: BookEntity): Completable

    fun deleteBookmarkByIsbn13(isbn13: String): Completable
}

enum class FILTER_TYPE {
    PRICE_ASC, PRICE_DESC, ALPHABET_ASC, ALPHABET_DESC
}