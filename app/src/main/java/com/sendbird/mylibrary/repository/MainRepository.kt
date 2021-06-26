package com.sendbird.mylibrary.repository

import com.google.gson.JsonObject
import com.sendbird.mylibrary.data.remote.model.Book
import com.sendbird.mylibrary.data.remote.model.RespNewBooks
import io.reactivex.Maybe
import io.reactivex.Single

interface MainRepository {

    fun fetchNew(): Single<RespNewBooks>

    fun fetchSearch(query: String, page: String): Maybe<List<Book>>

    fun fetchDetail(isbn13: String): Single<JsonObject>
}