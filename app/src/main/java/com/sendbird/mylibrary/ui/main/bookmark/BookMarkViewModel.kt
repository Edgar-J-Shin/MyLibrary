package com.sendbird.mylibrary.ui.main.bookmark

import com.sendbird.mylibrary.core.base.BaseViewModel
import com.sendbird.mylibrary.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookMarkViewModel @Inject constructor(private val searchRepository: SearchRepository) : BaseViewModel() {

}