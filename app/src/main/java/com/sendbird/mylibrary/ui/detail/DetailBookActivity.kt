package com.sendbird.mylibrary.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import com.sendbird.mylibrary.R
import com.sendbird.mylibrary.core.base.BaseActivity
import com.sendbird.mylibrary.databinding.ActivityDetailBookBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailBookActivity : BaseActivity<ActivityDetailBookBinding>(R.layout.activity_detail_book) {

    private val detailBookViewModel by viewModels<DetailBookViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding {
            lifecycleOwner = this@DetailBookActivity
            viewModel = this@DetailBookActivity.detailBookViewModel
        }

        observeViewModel()

        intent?.getStringExtra(INTENT_EXTRA_ISBN13)?.let {
            detailBookViewModel.getDetailBook(it)
        } ?: kotlin.run {
            // Invalid isbn13 value
            finish()
        }
    }

    private fun observeViewModel() {

        detailBookViewModel.viewEvent.observe(this, {
            it.getContentIfNotHandled()?.let { event ->
//                when (event) {
//
//                }
            }
        })
    }

    companion object {
        val INTENT_EXTRA_ISBN13 = "intent_extra_isbn13"
    }
}