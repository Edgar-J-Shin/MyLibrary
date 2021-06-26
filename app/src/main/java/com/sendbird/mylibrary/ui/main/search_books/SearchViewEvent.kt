package com.sendbird.mylibrary.ui.main.search_books

sealed class SearchViewEvent {
    object ShowEmptyView : SearchViewEvent()
    object ShowResultView : SearchViewEvent()
    object ShowHistoryView : SearchViewEvent()

    object HideKeyboard : SearchViewEvent()
}