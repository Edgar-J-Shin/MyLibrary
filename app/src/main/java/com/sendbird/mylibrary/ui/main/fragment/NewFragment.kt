package com.sendbird.mylibrary.ui.main.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.sendbird.mylibrary.R
import com.sendbird.mylibrary.core.base.BaseFragment
import com.sendbird.mylibrary.databinding.FragmentNewBinding
import com.sendbird.mylibrary.ui.main.MainViewModel

class NewFragment : BaseFragment<FragmentNewBinding>(R.layout.fragment_new) {

    private val newViewModel by viewModels<NewViewModel>()
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
        fun newInstance() = NewFragment()
    }
}