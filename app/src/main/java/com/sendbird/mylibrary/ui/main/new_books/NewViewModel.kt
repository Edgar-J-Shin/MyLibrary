package com.sendbird.mylibrary.ui.main.new_books

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sendbird.mylibrary.core.base.BaseViewModel
import com.sendbird.mylibrary.core.util.SchedulersFacade
import com.sendbird.mylibrary.model.Book
import com.sendbird.mylibrary.repository.SearchRepository
import com.sendbird.mylibrary.ui.ViewEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxkotlin.plusAssign
import javax.inject.Inject

@HiltViewModel
class NewViewModel @Inject constructor(private val searchRepository: SearchRepository) : BaseViewModel() {

    // Data
    private val _books: MutableLiveData<List<Book>> = MutableLiveData()
    val books: LiveData<List<Book>>
        get() = _books

    init {
        getNewBooks()
    }

    // Request
    fun getNewBooks() {
        // 전체 리스트 요청 이벤트 처리
        compositeDisposable += searchRepository.fetchNew()
            .subscribeOn(SchedulersFacade.IO)
            .observeOn(SchedulersFacade.UI)
            .doOnSubscribe { viewEvent(ViewEvent.ShowLoadingView) }
            .doOnEvent { _, _ -> viewEvent(ViewEvent.HideLoadingView) }
            .subscribe({
                if (it.error == "0") {
                    _books.value = it.books
                }
            }) {
                viewEvent(ViewEvent.ShowErrorDialog(it))
            }
    }
}