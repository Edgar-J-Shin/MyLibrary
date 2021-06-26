package com.sendbird.mylibrary.ui.detail

import com.sendbird.mylibrary.core.base.BaseViewModel
import com.sendbird.mylibrary.core.util.SchedulersFacade
import com.sendbird.mylibrary.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxkotlin.plusAssign
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailBookViewModel @Inject constructor(private val mainRepository: MainRepository) : BaseViewModel() {

    // Request : get Detail book infomation by isbn13 from remote
    fun getDetailBook(isbn13: String) {
        compositeDisposable += mainRepository.fetchDetail(isbn13)
            .observeOn(SchedulersFacade.UI)
            .subscribe({

            }) {
                Timber.e("error ${it.message}")
            }
    }

}