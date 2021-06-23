package com.sendbird.mylibrary.data.remote.service

import com.google.gson.JsonObject
import com.sendbird.mylibrary.data.remote.model.RespNewBooks
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface MainService {

    /**
     * 리스트를 가져온다
     * */
    @GET("new")
    fun fetchNew(): Single<RespNewBooks>

    @GET("search/{query}/{page}")
    fun fetchSearch(
        @Path("query") query: String,
        @Path("page") page: String
    ): Single<JsonObject>

    @GET("books/{isbn13}")
    fun fetchDetail(
        @Path("isbn13") isbn13: String
    ): Single<JsonObject>
}