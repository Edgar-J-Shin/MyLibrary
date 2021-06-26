package com.sendbird.mylibrary.ui.main.search_books.search_history

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.sendbird.mylibrary.R
import com.sendbird.mylibrary.core.base.BaseFragment
import com.sendbird.mylibrary.databinding.FragmentSearchHistoryBinding
import com.sendbird.mylibrary.ui.main.search_books.SearchViewModel
import com.sendbird.mylibrary.ui.main.search_books.search_history.adapter.SearchHistoryAdapter

class SearchHistoryFragment : BaseFragment<FragmentSearchHistoryBinding>(R.layout.fragment_search_history) {

    private val searchViewModel by activityViewModels<SearchViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBinding {
            lifecycleOwner = viewLifecycleOwner
            viewModel = searchViewModel
        }

        setupRecyclerView()
        observeViewModel()
    }


    private fun setupRecyclerView() {
        binding.rvSearchHistory.apply {
            setHasFixedSize(true)
            adapter = SearchHistoryAdapter(
                searchKeyword = {
                    searchViewModel.searchByKeyword(it)
                }, deleteKeyword = {
                    searchViewModel.deleteHistoryByKeyword(it)
                })
        }
    }

    private fun observeViewModel() {
        searchViewModel.history.observe(viewLifecycleOwner, {
            (binding.rvSearchHistory.adapter as SearchHistoryAdapter).apply {
                submitList(it)
                notifyDataSetChanged()
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = SearchHistoryFragment()
    }
}