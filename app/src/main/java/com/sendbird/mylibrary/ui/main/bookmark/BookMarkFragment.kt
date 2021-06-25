package com.sendbird.mylibrary.ui.main.bookmark

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.sendbird.mylibrary.R
import com.sendbird.mylibrary.core.base.BaseFragment
import com.sendbird.mylibrary.databinding.FragmentBookMarkBinding
import com.sendbird.mylibrary.ui.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookMarkFragment : BaseFragment<FragmentBookMarkBinding>(R.layout.fragment_book_mark) {

    private val newViewModel by viewModels<BookMarkViewModel>()
    private val mainViewModel by activityViewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBinding {
            lifecycleOwner = viewLifecycleOwner

        }

        observeViewModel()
    }

    private fun observeViewModel() {

    }

    companion object {
        @JvmStatic
        fun newInstance() = BookMarkFragment()
    }
}