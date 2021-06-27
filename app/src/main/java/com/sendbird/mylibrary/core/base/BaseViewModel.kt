package com.sendbird.mylibrary.core.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sendbird.mylibrary.core.util.Event
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    protected val compositeDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    // View Event 처리를 위한 LiveData
    private val _viewEvent = MutableLiveData<Event<Any>>()
    val viewEvent: LiveData<Event<Any>>
        get() = _viewEvent

    // View Event 호출
    fun viewEvent(content: Any) {
        _viewEvent.value = Event(content)
    }

    override fun onCleared() {
        super.onCleared()
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
}