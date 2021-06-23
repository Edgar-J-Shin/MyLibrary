package com.sendbird.mylibrary.data.remote

import com.google.gson.JsonObject
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface MainService {

    /**
     * 리스트를 가져온다
     * */
    @GET("new")
    fun fetchNew(): Single<JsonObject>

    @GET("search/{query}/{page}")
    fun fetchSearch(
        @Path("query") query: String,
        @Path("page") page: String
    ): Single<JsonObject>

    @GET("books/{isbn}")
    fun fetchDetail(
        @Path("isbn") isbn: String
    ): Single<JsonObject>
}