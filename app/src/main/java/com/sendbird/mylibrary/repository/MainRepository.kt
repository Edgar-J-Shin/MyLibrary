package com.sendbird.mylibrary.repository

import com.google.gson.JsonObject
import io.reactivex.Single

interface MainRepository {

    fun fetchNew(): Single<JsonObject>

    fun fetchSearch(query: String, page: String): Single<JsonObject>

    fun fetchDetail(isbn: String): Single<JsonObject>
}