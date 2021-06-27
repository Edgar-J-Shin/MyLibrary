package com.sendbird.mylibrary.ui.main.search_books

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sendbird.mylibrary.MyLibraryApp
import com.sendbird.mylibrary.core.base.BaseViewModel
import com.sendbird.mylibrary.core.util.SchedulersFacade
import com.sendbird.mylibrary.model.Book
import com.sendbird.mylibrary.repository.SearchRepository
import com.sendbird.mylibrary.ui.ViewEvent
import com.sendbird.mylibrary.ui.main.search_books.search_history.data.SearchHistoryItem
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.rxkotlin.plusAssign
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchRepository: SearchRepository) : BaseViewModel() {

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

    init {
        // 처음 진입 시, 기존에 검색했던 키워드 리스트를 보여준다.
        getSearchHistory()
    }

    // View Action : Search Button Click
    fun onSearchClick() {
        if (keyword.value?.isNotEmpty() == true) {
            searchBooks()
        } else {
            viewEvent(ViewEvent.HideKeyboard)
            viewEvent(SearchViewEvent.ShowHistoryView)
        }
    }

    // View Action : ClearAll Button Click
    fun onClearSearchHistoryClick() {
        clearSearchHistory()
    }

    fun onKeywordChanged(text: CharSequence) {
        // Input Edit Text에 아무것도 입력되지 않았을 경우에는 History View를 보여준다.
        if (text.isEmpty()) {
            viewEvent(SearchViewEvent.ShowHistoryView)
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
            .map { history -> history.map { SearchHistoryItem(it) } }
            .observeOn(SchedulersFacade.UI)
            .doOnSubscribe {
                // 로딩 뷰를 보여준다.
                viewEvent(ViewEvent.ShowLoadingView)
                // 검색 히스토리 요청 시 Show History View 요청을 한다.
                viewEvent(SearchViewEvent.ShowHistoryView)
            }
            .doOnEvent { _, _ -> viewEvent(ViewEvent.HideLoadingView) }
            .subscribe({
                _history.value = it.toMutableList()
            }) {
                viewEvent(ViewEvent.ShowErrorDialog(it))
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
            .subscribe({
                _history.value = mutableListOf()
            }) {
                viewEvent(ViewEvent.ShowErrorDialog(it))
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
            .subscribe({
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
            }) {
                viewEvent(ViewEvent.ShowErrorDialog(it))
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
            .subscribe({
                _history.value?.let { history ->
                    _history.value = history.apply {
                        remove(SearchHistoryItem(keyword))
                    }
                }
            }) {
                viewEvent(ViewEvent.ShowErrorDialog(it))
            }
    }

    // 마지막에 요청한 키워드를 기준으로 페이징 처리를 할 수 있도록 키워드 유지
    lateinit var finalRequestKeyword: String

    // Request : Search books by keyword from remote
    fun searchBooks(page: Int = 1) {
        if (page == 1) {
            finalRequestKeyword = keyword.value ?: ""
        }

        compositeDisposable += searchRepository.fetchSearch(finalRequestKeyword, page.toString())
            .subscribeOn(SchedulersFacade.IO)
            .observeOn(SchedulersFacade.UI)
            .doOnSubscribe {
                // 검색 요청 시 키보드를 내린다.
                viewEvent(ViewEvent.HideKeyboard)

                // 새로운 검색 요청 시 동작
                if (page == 1) {
                    viewEvent(ViewEvent.ShowLoadingView)
                    // Show Result View 요청을 한다.
                    viewEvent(SearchViewEvent.ShowResultView)
                    // 요청한 keyword를 저장한다.
                    insertSearchHistory(finalRequestKeyword)
                }
            }
            .doOnEvent { _, _ -> viewEvent(ViewEvent.HideLoadingView) }
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
                            viewEvent(ViewEvent.ShowErrorDialog(Throwable("It's wrong page : $page")))
                        }
                    }
                } else {
                    // 검색 결과가 없을 경우 Empty View를 보여준다.
                    viewEvent(SearchViewEvent.ShowEmptyView)
                }
            }) {
                viewEvent(ViewEvent.ShowErrorDialog(it))
            }
    }
}