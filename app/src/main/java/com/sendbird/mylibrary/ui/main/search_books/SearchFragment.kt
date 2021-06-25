package com.sendbird.mylibrary.ui.main.search_books

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView.OnEditorActionListener
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.sendbird.mylibrary.R
import com.sendbird.mylibrary.core.base.BaseFragment
import com.sendbird.mylibrary.core.util.OnLoadMoreListener
import com.sendbird.mylibrary.databinding.FragmentSearchBinding
import com.sendbird.mylibrary.ui.main.MainViewModel
import com.sendbird.mylibrary.ui.main.adapter.BookAdapter
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    private val PAGE_SIZE = 10

    private val searchViewModel by viewModels<SearchViewModel>()
    private val mainViewModel by activityViewModels<MainViewModel>()

    private val loadMoreListener = OnLoadMoreListener {
        searchViewModel.searchBooks(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBinding {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@SearchFragment.searchViewModel
        }

        setupRecyclerView()
        setupKeyboardEvent()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        binding.rvSearch.apply {
            setHasFixedSize(true)
            addOnScrollListener(loadMoreListener)
            adapter = BookAdapter()
        }
    }

    private fun setupKeyboardEvent() {
        binding.tietSearch.setOnEditorActionListener(OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchViewModel.onSearchClick()
                return@OnEditorActionListener true
            }
            false
        })
    }

    private fun observeViewModel() {
        searchViewModel.books.observe(viewLifecycleOwner, {
            (binding.rvSearch.adapter as BookAdapter).apply {
                submitList(it)
                notifyItemRangeInserted(it.count() - PAGE_SIZE, it.count())

                loadMoreListener.resetLoading()
            }
        })

        searchViewModel.showViewAction.observe(viewLifecycleOwner, {
            it.getContentIfNotHandled()?.let { event ->
                when (event) {
                    SearchViewModel.EVENT_SHOW_EMPTY_VIEW -> showEmptyView()
                    SearchViewModel.EVENT_HIDE_KEYBOARD -> hideKeyboard()
                    SearchViewModel.EVENT_CLEAR_LIST -> clearList()
                }
            }
        })
    }

    private fun showEmptyView() {

    }

    private fun clearList() {
        (binding.rvSearch.adapter as BookAdapter).apply {
            submitList(null)
            notifyDataSetChanged()
        }
    }

    private fun hideKeyboard() {
        binding.tietSearch.run {
            clearFocus()
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(windowToken, 0)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = SearchFragment()
    }
}