package com.sendbird.mylibrary.repository

import com.google.gson.JsonObject
import com.sendbird.mylibrary.data.remote.model.RespNewBooks
import io.reactivex.Single

interface MainRepository {

    fun fetchNew(): Single<RespNewBooks>

    fun fetchSearch(query: String, page: String): Single<JsonObject>

    fun fetchDetail(isbn13: String): Single<JsonObject>
}