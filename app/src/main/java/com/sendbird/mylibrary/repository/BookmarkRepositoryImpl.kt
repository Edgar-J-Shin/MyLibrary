package com.sendbird.mylibrary.repository

import com.sendbird.mylibrary.data.local.dao.BookmarkDao
import com.sendbird.mylibrary.data.local.entity.BookEntity
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class BookmarkRepositoryImpl @Inject constructor(private val bookmarkDao: BookmarkDao) : BookmarkRepository {

    override fun getBookmarkListBySort(filterType: FILTER_TYPE): Single<List<BookEntity>> {
        return when (filterType) {
            FILTER_TYPE.PRICE_ASC -> bookmarkDao.selectByPrice(true)
            FILTER_TYPE.PRICE_DESC -> bookmarkDao.selectByPrice(false)
            FILTER_TYPE.ALPHABET_ASC -> bookmarkDao.selectByAlphabet(true)
            FILTER_TYPE.ALPHABET_DESC -> bookmarkDao.selectByAlphabet(false)
            else -> bookmarkDao.select()
        }
    }

    override fun getBookmarkByIsbn13(isbn13: String): Single<List<BookEntity>> {
        return bookmarkDao.selectByIsbn13(isbn13)
    }

    override fun insertBookmark(bookEntity: BookEntity): Single<Long> {
        return bookmarkDao.insert(bookEntity)
    }

    override fun deleteBookmarkByIsbn13(isbn13: String): Completable {
        return bookmarkDao.deleteByIsbn13(isbn13)
    }

    override fun deleteBookmark(bookEntity: BookEntity): Completable {
        return bookmarkDao.delete(bookEntity)
    }
}