package com.sendbird.mylibrary.repository

import com.sendbird.mylibrary.model.Book
import com.sendbird.mylibrary.data.remote.model.RespDetailBook
import com.sendbird.mylibrary.data.remote.model.RespNewBooks
import io.reactivex.Maybe
import io.reactivex.Single

interface SearchRepository {

    fun fetchNew(): Single<RespNewBooks>

    fun fetchSearch(query: String, page: String): Maybe<List<Book>>

    fun fetchDetail(isbn13: String): Maybe<RespDetailBook>
}