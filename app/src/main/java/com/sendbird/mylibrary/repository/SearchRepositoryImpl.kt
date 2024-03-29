package com.sendbird.mylibrary.repository

import com.sendbird.mylibrary.core.util.SchedulersFacade
import com.sendbird.mylibrary.model.Book
import com.sendbird.mylibrary.data.remote.model.RespDetailBook
import com.sendbird.mylibrary.data.remote.model.RespNewBooks
import com.sendbird.mylibrary.data.remote.service.SearchService
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val searchService: SearchService) : SearchRepository {
    override fun fetchNew(): Single<RespNewBooks> =
        searchService.fetchNew()

    override fun fetchSearch(query: String, page: String): Maybe<List<Book>> =
        searchService.fetchSearch(query, page)
            .filter { it.error == "0" }
            .map { it.books }

    override fun fetchDetail(isbn13: String): Maybe<RespDetailBook> =
        searchService.fetchDetail(isbn13)
            .filter { it.error == "0" }
}