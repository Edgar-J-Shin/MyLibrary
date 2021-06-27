package com.sendbird.mylibrary.ui.main.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sendbird.mylibrary.core.base.BaseViewModel
import com.sendbird.mylibrary.core.util.SchedulersFacade
import com.sendbird.mylibrary.data.local.entity.mapToBook
import com.sendbird.mylibrary.model.Book
import com.sendbird.mylibrary.model.mapToBookEntity
import com.sendbird.mylibrary.repository.BookmarkRepository
import com.sendbird.mylibrary.repository.FILTER_TYPE
import com.sendbird.mylibrary.ui.ViewEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxkotlin.plusAssign
import javax.inject.Inject

@HiltViewModel
class BookMarkViewModel @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
) : BaseViewModel() {

    // Data : Filter type data
    private val _filterType: MutableLiveData<FILTER_TYPE> = MutableLiveData(FILTER_TYPE.ALPHABET_ASC)
    val filterType: LiveData<FILTER_TYPE>
        get() = _filterType

    fun setFilterType(type: FILTER_TYPE) {
        _filterType.value = type
    }

    // Data : Bookmark list
    private val _books: MutableLiveData<List<Book>> = MutableLiveData()
    val books: LiveData<List<Book>>
        get() = _books

    init {
        filterType.value?.let { getBookmarkAll(it) }
    }

    fun onClickFilterPicker() {
        viewEvent(BookmarkViewEvent.ShowFilterView)
    }

    fun getBookmarkAll(filterType: FILTER_TYPE) {
        compositeDisposable += bookmarkRepository.getBookmarkListBySort(filterType)
            .subscribeOn(SchedulersFacade.IO)
            .map { bookmarks -> bookmarks.map { it.mapToBook() } }
            .observeOn(SchedulersFacade.UI)
            .doOnSubscribe { viewEvent(ViewEvent.ShowLoadingView) }
            .doOnEvent { _, _ -> viewEvent(ViewEvent.HideLoadingView) }
            .subscribe({
                _books.value = it.toMutableList()
            }) {
                viewEvent(ViewEvent.ShowErrorDialog(it))
            }
    }

    // Request : insert bookmark into database
    private fun insertBookmark(book: Book) {
        compositeDisposable += bookmarkRepository.insertBookmark(book.mapToBookEntity())
            .subscribeOn(SchedulersFacade.IO)
            .observeOn(SchedulersFacade.UI)
            .subscribe({
            }) {
                viewEvent(ViewEvent.ShowErrorDialog(it))
            }
    }

    // Request : delete bookmark from database
    private fun deleteBookmark(book: Book) {
        compositeDisposable += bookmarkRepository.deleteBookmarkByIsbn13(book.isbn13)
            .subscribeOn(SchedulersFacade.IO)
            .observeOn(SchedulersFacade.UI)
            .subscribe({}) {
                viewEvent(ViewEvent.ShowErrorDialog(it))
            }
    }
}