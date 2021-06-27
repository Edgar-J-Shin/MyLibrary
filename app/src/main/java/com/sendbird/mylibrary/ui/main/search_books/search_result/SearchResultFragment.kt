package com.sendbird.mylibrary.ui.main.search_books.search_result

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.sendbird.mylibrary.R
import com.sendbird.mylibrary.core.base.BaseFragment
import com.sendbird.mylibrary.core.util.OnLoadMoreListener
import com.sendbird.mylibrary.databinding.FragmentSearchResultBinding
import com.sendbird.mylibrary.ui.detail.DetailBookActivity
import com.sendbird.mylibrary.ui.main.adapter.BookAdapter
import com.sendbird.mylibrary.ui.main.search_books.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchResultFragment : BaseFragment<FragmentSearchResultBinding>(R.layout.fragment_search_result) {

    private val PAGE_SIZE = 10

    private val searchViewModel by activityViewModels<SearchViewModel>()

    private val loadMoreListener = OnLoadMoreListener {
        searchViewModel.searchBooks(it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBinding {
            lifecycleOwner = viewLifecycleOwner
        }

        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        binding.rvSearchResult.apply {
            setHasFixedSize(true)
            addOnScrollListener(loadMoreListener)
            adapter = BookAdapter(showDetails = { isbn13 ->
                startActivity(Intent(context, DetailBookActivity::class.java).apply {
                    putExtra(DetailBookActivity.INTENT_EXTRA_ISBN13, isbn13)
                })
            })
        }
    }

    private fun observeViewModel() {
        searchViewModel.books.observe(viewLifecycleOwner, {
            (binding.rvSearchResult.adapter as BookAdapter).apply {
                val count = it.count()
                if (count <= PAGE_SIZE) {
                    submitList(null)
                }

                submitList(it)
                notifyItemRangeInserted(count - PAGE_SIZE, count)

                loadMoreListener.resetLoading()
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = SearchResultFragment()
    }
}