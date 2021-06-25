package com.sendbird.mylibrary.ui.main.search_books.data

import com.sendbird.mylibrary.data.remote.model.Book

sealed class SearchViewData {

    object NoResultViewData : SearchViewData()

    class SearchHistoryViewData(val data: List<KeywordSearchHistoryItem>) : SearchViewData()

    class SearchResultViewData(val data: List<Book>) : SearchViewData()
}
