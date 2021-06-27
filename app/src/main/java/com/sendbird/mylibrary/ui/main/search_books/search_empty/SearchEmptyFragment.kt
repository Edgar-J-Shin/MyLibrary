package com.sendbird.mylibrary.ui.main.search_books.search_empty

import android.os.Bundle
import android.view.View
import com.sendbird.mylibrary.R
import com.sendbird.mylibrary.core.base.BaseFragment
import com.sendbird.mylibrary.databinding.FragmentSearchEmptyBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchEmptyFragment : BaseFragment<FragmentSearchEmptyBinding>(R.layout.fragment_search_empty) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBinding {
            lifecycleOwner = viewLifecycleOwner
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = SearchEmptyFragment()
    }
}