package com.sendbird.mylibrary.ui.main.new_books

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sendbird.mylibrary.core.base.BaseViewModel
import com.sendbird.mylibrary.data.remote.model.Book
import com.sendbird.mylibrary.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class NewViewModel @Inject constructor(private val mainRepository: MainRepository) : BaseViewModel() {

    init {
//        getNewBooks()
    }

    // Data
    private val _books: MutableLiveData<List<Book>> = MutableLiveData()
    val books: LiveData<List<Book>>
        get() = _books

    // View Action


    // Request
    fun getNewBooks() {
        // 전체 리스트 요청 이벤트 처리
        compositeDisposable += mainRepository.fetchNew()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.error == "0") {
                    _books.value = it.books
                }
            }) {
                Timber.e("error ${it.message}")
            }
    }
}