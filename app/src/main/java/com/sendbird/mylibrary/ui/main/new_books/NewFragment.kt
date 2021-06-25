package com.sendbird.mylibrary.ui.main.new_books

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.sendbird.mylibrary.R
import com.sendbird.mylibrary.core.base.BaseFragment
import com.sendbird.mylibrary.databinding.FragmentNewBinding
import com.sendbird.mylibrary.ui.main.MainViewModel
import com.sendbird.mylibrary.ui.main.adapter.BookAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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

        setupRecyclerView()

        observeViewModel()
    }

    override fun onResume() {
        super.onResume()

        newViewModel.getNewBooks()
    }

    private fun setupRecyclerView() {
        binding.rvNewBooks.apply {
            adapter = BookAdapter()
            setHasFixedSize(true)
        }
    }

    private fun observeViewModel() {
        newViewModel.books.observe(viewLifecycleOwner, {
            (binding.rvNewBooks.adapter as BookAdapter).submitList(it)
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = NewFragment()
    }
}