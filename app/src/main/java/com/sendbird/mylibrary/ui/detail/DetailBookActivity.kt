package com.sendbird.mylibrary.ui.detail

import android.os.Bundle
import android.view.MenuItem
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

        // 액션바 타이틀, 백버튼 설정
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = getString(R.string.detail_title)
        }

        observeViewModel()

        // intent 처리
        intent?.getStringExtra(INTENT_EXTRA_ISBN13)?.let {
            detailBookViewModel.getDetailBook(it)
        } ?: kotlin.run {
            // Invalid isbn13 value
            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            // 액션바의 뒤로가기 버튼 동작
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun observeViewModel() {
        detailBookViewModel.detailBook.observe(this, {
            binding.apply {
                item = it
            }
        })
    }

    companion object {
        const val INTENT_EXTRA_ISBN13 = "intent_extra_isbn13"
    }
}