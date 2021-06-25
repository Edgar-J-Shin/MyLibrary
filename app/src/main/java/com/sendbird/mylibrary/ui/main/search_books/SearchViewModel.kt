package com.sendbird.mylibrary.ui.main.search_books

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sendbird.mylibrary.core.base.BaseViewModel
import com.sendbird.mylibrary.core.util.Event
import com.sendbird.mylibrary.core.util.SchedulersFacade
import com.sendbird.mylibrary.data.remote.model.Book
import com.sendbird.mylibrary.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxkotlin.plusAssign
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val mainRepository: MainRepository) : BaseViewModel() {

    // Data
    val _keyword: MutableLiveData<String> = MutableLiveData()
    val keyword: LiveData<String>
        get() = _keyword

    private val _books: MutableLiveData<MutableList<Book>> = MutableLiveData()
    val books: LiveData<MutableList<Book>>
        get() = _books

    // Event

    private val _showViewAction = MutableLiveData<Event<Any>>()
    val showViewAction: LiveData<Event<Any>>
        get() = _showViewAction

    // View Action
    fun onSearchClick() {
        _showViewAction.value = Event(EVENT_HIDE_KEYBOARD)
        _showViewAction.value = Event(EVENT_CLEAR_LIST)

        searchBooks()
    }

    // Request
    fun searchBooks(page: Int = 1) {
        // 전체 리스트 요청 이벤트 처리
        compositeDisposable += mainRepository.fetchSearch(keyword.value ?: "", page.toString())
            .observeOn(SchedulersFacade.UI)
            .subscribe({
                if (page == 1) {
                    if (it.data.isEmpty()) {
                        _showViewAction.value = Event(EVENT_SHOW_EMPTY_VIEW)
                    } else {
                        _books.value = it.data.toMutableList()
                    }
                } else {
                    val oldBooks = _books.value
                    _books.value = oldBooks?.apply {
                        addAll(it.data)
                    }
                }
            }) {
                Timber.e("error ${it.message}")
            }
    }

    companion object {
        const val EVENT_SHOW_EMPTY_VIEW = 10000
        const val EVENT_CLEAR_LIST = 10010
        const val EVENT_HIDE_KEYBOARD = 11000
    }

}