package com.sendbird.mylibrary.repository

import com.google.gson.JsonObject
import com.sendbird.mylibrary.core.util.SchedulersFacade
import com.sendbird.mylibrary.data.remote.model.Book
import com.sendbird.mylibrary.data.remote.model.RespDetailBook
import com.sendbird.mylibrary.data.remote.model.RespNewBooks
import com.sendbird.mylibrary.data.remote.service.MainService
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val mainService: MainService) : MainRepository {
    override fun fetchNew(): Single<RespNewBooks> =
        mainService.fetchNew()
            .subscribeOn(SchedulersFacade.IO)

    override fun fetchSearch(query: String, page: String): Maybe<List<Book>> =
        mainService.fetchSearch(query, page)
            .filter { it.error == "0" }
            .map { it.books }
            .subscribeOn(SchedulersFacade.IO)

    override fun fetchDetail(isbn13: String): Maybe<RespDetailBook> =
        mainService.fetchDetail(isbn13)
            .filter { it.error == "0" }
            .subscribeOn(SchedulersFacade.IO)
}