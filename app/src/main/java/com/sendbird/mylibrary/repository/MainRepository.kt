package com.sendbird.mylibrary.repository

import com.google.gson.JsonObject
import com.sendbird.mylibrary.data.remote.model.RespNewBooks
import com.sendbird.mylibrary.ui.main.search_books.data.SearchViewData
import io.reactivex.Maybe
import io.reactivex.Single

interface MainRepository {

    fun fetchNew(): Single<RespNewBooks>

    fun fetchSearch(query: String, page: String): Maybe<SearchViewData.SearchResultViewData>

    fun fetchDetail(isbn13: String): Single<JsonObject>
}