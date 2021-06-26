package com.sendbird.mylibrary.data.remote.service

import com.google.gson.JsonObject
import com.sendbird.mylibrary.data.remote.model.RespNewBooks
import com.sendbird.mylibrary.data.remote.model.RespSearchBooks
import io.reactivex.Maybe
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface MainService {

    /**
     * 새로운 서적 리스트를 가져온다
     */
    @GET("new")
    fun fetchNew(): Single<RespNewBooks>

    /**
     * 특정 키워드에 대한 서적 검색 정보를 가져온다
     *
     * @param query 특정 키워드
     * @param page 페이지
     *
     */
    @GET("search/{query}/{page}")
    fun fetchSearch(
        @Path("query") query: String,
        @Path("page") page: String
    ): Maybe<RespSearchBooks>

    /**
     * [Detail Book] 화면에서 선택된 북마크 리스트를 가져온다
     *
     * @param isbn13 isbn13
     *
     */
    @GET("books/{isbn13}")
    fun fetchDetail(
        @Path("isbn13") isbn13: String
    ): Single<JsonObject>
}