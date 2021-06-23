package com.sendbird.mylibrary.repository

import com.google.gson.JsonObject
import com.sendbird.mylibrary.data.remote.model.RespNewBooks
import com.sendbird.mylibrary.data.remote.service.MainService
import com.sendbird.mylibrary.utils.SchedulersFacade
import io.reactivex.Single
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val mainService: MainService) : MainRepository {
    override fun fetchNew(): Single<RespNewBooks> =
        mainService.fetchNew()
            .subscribeOn(SchedulersFacade.IO)

    override fun fetchSearch(query: String, page: String): Single<JsonObject> {
        TODO("Not yet implemented")
    }

    override fun fetchDetail(isbn13: String): Single<JsonObject> {
        TODO("Not yet implemented")
    }
}