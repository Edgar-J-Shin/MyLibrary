package com.sendbird.mylibrary.ui.main.search_books

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sendbird.mylibrary.MyLibraryApp
import com.sendbird.mylibrary.core.base.BaseViewModel
import com.sendbird.mylibrary.core.util.Event
import com.sendbird.mylibrary.core.util.SchedulersFacade
import com.sendbird.mylibrary.data.remote.model.Book
import com.sendbird.mylibrary.repository.MainRepository
import com.sendbird.mylibrary.ui.main.search_books.search_history.data.SearchHistoryItem
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.rxkotlin.plusAssign
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val mainRepository: MainRepository) : BaseViewModel() {

    // Data : input text
    val _keyword: MutableLiveData<String> = MutableLiveData()
    val keyword: LiveData<String>
        get() = _keyword

    // Data : Search result
    private val _books: MutableLiveData<MutableList<Book>> = MutableLiveData()
    val books: LiveData<MutableList<Book>>
        get() = _books

    // Data : Search history
    private val _history: MutableLiveData<MutableList<SearchHistoryItem>> = MutableLiveData()
    val history: LiveData<MutableList<SearchHistoryItem>>
        get() = _history

    // Event : View Action for SearchViewEvent
    private val _viewAction = MutableLiveData<Event<SearchViewEvent>>()
    val viewAction: LiveData<Event<SearchViewEvent>>
        get() = _viewAction

    init {
        // 처음 진입 시, 기존에 검색했던 키워드 리스트를 보여준다.
        getSearchHistory()
    }

    // View Action : Search Button Click
    fun onSearchClick() {
        if (keyword.value?.isNotEmpty() == true) {
            searchBooks()
        } else {
            _viewAction.value = Event(SearchViewEvent.HideKeyboard)
            _viewAction.value = Event(SearchViewEvent.ShowHistoryView)
        }
    }

    // View Action : ClearAll Button Click
    fun onClearSearchHistoryClick() {
        clearSearchHistory()
    }

    fun onKeywordChanged(text: CharSequence) {
        // Input Edit Text에 아무것도 입력되지 않았을 경우에는 History View를 보여준다.
        if (text.isEmpty()) {
            _viewAction.value = Event(SearchViewEvent.ShowHistoryView)
        }
    }

    fun searchByKeyword(keyword: String) {
        _keyword.value = keyword
        searchBooks()
    }

    fun deleteHistoryByKeyword(keyword: String) {
        deleteSearchHistory(keyword)
    }

    // Reqeust : Search history from local
    private fun getSearchHistory() {
        compositeDisposable += Single.create<List<String>> {
            if (!it.isDisposed) {
                it.onSuccess(MyLibraryApp.getKeywordAll())
            }
        }
            .subscribeOn(SchedulersFacade.IO)
            .doOnSubscribe {
                // 검색 히스토리 요청 시 Show History View 요청을 한다.
                _viewAction.value = Event(SearchViewEvent.ShowHistoryView)
            }
            .observeOn(SchedulersFacade.UI)
            .map { history -> history.map { SearchHistoryItem(it) } }
            .subscribe({
                _history.value = it.toMutableList()
            }) {
                Timber.e("error ${it.message}")
            }
    }

    // Reqeust : Clear search history from local
    private fun clearSearchHistory() {
        compositeDisposable += Completable.create {
            if (!it.isDisposed) {
                MyLibraryApp.clearKeywordAll()
                it.onComplete()
            }
        }
            .subscribeOn(SchedulersFacade.IO)
            .observeOn(SchedulersFacade.UI)
            .subscribe {
                _history.value = mutableListOf()
            }
    }

    // Reqeust : Delete search history item from local
    private fun insertSearchHistory(keyword: String) {
        compositeDisposable += Completable.create {
            if (!it.isDisposed) {
                MyLibraryApp.insertKeyword(keyword)
                it.onComplete()
            }
        }
            .subscribeOn(SchedulersFacade.IO)
            .observeOn(SchedulersFacade.UI)
            .subscribe {
                _history.value?.let { history ->
                    _history.value = history.apply {
                        val item = SearchHistoryItem(keyword)
                        if (contains(item)) {
                            remove(item)
                        }
                        // 마지막에 검색한 keyword가 최상위로 올라오도록 인덱스 지정
                        add(0, item)
                    }
                }
            }
    }

    // Reqeust : Delete search history item from local
    private fun deleteSearchHistory(keyword: String) {
        compositeDisposable += Completable.create {
            if (!it.isDisposed) {
                MyLibraryApp.deleteKeyword(keyword)
                it.onComplete()
            }
        }
            .subscribeOn(SchedulersFacade.IO)
            .observeOn(SchedulersFacade.UI)
            .subscribe {
                _history.value?.let { history ->
                    _history.value = history.apply {
                        remove(SearchHistoryItem(keyword))
                    }
                }
            }
    }

    // 마지막에 요청한 키워드를 기준으로 페이징 처리를 할 수 있도록 키워드 유지
    lateinit var finalRequestKeyword: String

    // Request : Search books by keyword from remote
    fun searchBooks(page: Int = 1) {
        if (page == 1) {
            finalRequestKeyword = keyword.value ?: ""
        }

        compositeDisposable += mainRepository.fetchSearch(finalRequestKeyword, page.toString())
            .observeOn(SchedulersFacade.UI)
            .doOnSubscribe {
                // 검색 요청 시 키보드를 내린다.
                _viewAction.value = Event(SearchViewEvent.HideKeyboard)
                // 새로운 검색 요청 시 동작
                if (page == 1) {
                    // Show Result View 요청을 한다.
                    _viewAction.value = Event(SearchViewEvent.ShowResultView)
                    // 요청한 keyword를 저장한다.
                    insertSearchHistory(finalRequestKeyword)
                }
            }
            .subscribe({
                if (it.isNotEmpty()) {
                    when {
                        page == 1 -> {
                            _books.value = it.toMutableList()
                        }
                        page > 1 -> {
                            _books.value?.let { books ->
                                _books.value = books.apply {
                                    addAll(it)
                                }
                            }
                        }
                        else -> {
                            // wrong case
                        }
                    }
                } else {
                    // 검색 결과가 없을 경우 Empty View를 보여준다.
                    _viewAction.value = Event(SearchViewEvent.ShowEmptyView)
                }
            }) {
                Timber.e("error ${it.message}")
            }
    }

    companion object {

    }

}