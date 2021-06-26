package com.sendbird.mylibrary.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sendbird.mylibrary.core.base.BaseViewModel
import com.sendbird.mylibrary.core.util.SchedulersFacade
import com.sendbird.mylibrary.data.remote.model.mapToDetailBook
import com.sendbird.mylibrary.model.DetailBook
import com.sendbird.mylibrary.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxkotlin.plusAssign
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailBookViewModel @Inject constructor(private val mainRepository: MainRepository) : BaseViewModel() {

    private val _detailBook: MutableLiveData<DetailBook> = MutableLiveData()
    val detailBook: LiveData<DetailBook>
        get() = _detailBook

    fun onClickBookmarkButton() {

    }

    // Request : get Detail book infomation by isbn13 from remote
    fun getDetailBook(isbn13: String) {
        compositeDisposable += mainRepository.fetchDetail(isbn13)
            .observeOn(SchedulersFacade.UI)
            .map { it.mapToDetailBook() }
            .subscribe({
                _detailBook.value = it
            }) {
                Timber.e("error ${it.message}")
            }
    }
}