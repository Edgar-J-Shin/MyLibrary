package com.sendbird.mylibrary.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sendbird.mylibrary.core.base.BaseViewModel
import com.sendbird.mylibrary.core.util.SchedulersFacade
import com.sendbird.mylibrary.data.remote.model.mapToDetailBook
import com.sendbird.mylibrary.model.Book
import com.sendbird.mylibrary.model.DetailBook
import com.sendbird.mylibrary.model.mapToBook
import com.sendbird.mylibrary.model.mapToBookEntity
import com.sendbird.mylibrary.repository.BookmarkRepository
import com.sendbird.mylibrary.repository.SearchRepository
import com.sendbird.mylibrary.ui.ViewEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Single
import io.reactivex.rxkotlin.plusAssign
import javax.inject.Inject

@HiltViewModel
class DetailBookViewModel @Inject constructor(
    private val searchRepository: SearchRepository,
    private val bookmarkRepository: BookmarkRepository
) : BaseViewModel() {

    // Data : Bookmark check value
    private val _bookmark: MutableLiveData<Boolean> = MutableLiveData(false)
    val bookmark: LiveData<Boolean>
        get() = _bookmark

    // Data : Detail book infomation
    private val _detailBook: MutableLiveData<DetailBook> = MutableLiveData()
    val detailBook: LiveData<DetailBook>
        get() = _detailBook

    // View Action : Bookmark Button Click
    fun onClickBookmarkButton(item: DetailBook) {
        if (bookmark.value == true) {
            _bookmark.value = false
            deleteBookmark(item.mapToBook())
        } else {
            _bookmark.value = true
            insertBookmark(item.mapToBook())
        }
    }

    // Request : get Detail book information by isbn13 from remote
    fun getDetailBook(isbn13: String) {
        compositeDisposable += getDetailBookRequest(isbn13)
            .subscribeOn(SchedulersFacade.IO)
            .observeOn(SchedulersFacade.UI)
            .doOnSubscribe { viewEvent(ViewEvent.ShowLoadingView) }
            .doOnEvent { _, _ -> viewEvent(ViewEvent.HideLoadingView) }
            .subscribe({
                _detailBook.value = it.first
                _bookmark.value = it.second
            }) {
                viewEvent(ViewEvent.ShowErrorDialog(it))
            }
    }

    private fun getDetailBookRequest(isbn13: String): Single<Pair<DetailBook, Boolean>> {
        return Single.zip(
            searchRepository.fetchDetail(isbn13)
                .subscribeOn(SchedulersFacade.IO)
                .toSingle(),
            bookmarkRepository.getBookmarkByIsbn13(isbn13)
                .subscribeOn(SchedulersFacade.IO),
            { detailBook, bookEntities ->
                detailBook.mapToDetailBook() to bookEntities.isNotEmpty()
            })
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