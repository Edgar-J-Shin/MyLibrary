package com.sendbird.mylibrary.ui.main.search_books

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView.OnEditorActionListener
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import com.sendbird.mylibrary.R
import com.sendbird.mylibrary.core.base.BaseFragment
import com.sendbird.mylibrary.databinding.FragmentSearchBinding
import com.sendbird.mylibrary.ui.main.search_books.search_empty.SearchEmptyFragment
import com.sendbird.mylibrary.ui.main.search_books.search_history.SearchHistoryFragment
import com.sendbird.mylibrary.ui.main.search_books.search_result.SearchResultFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    private val searchViewModel by activityViewModels<SearchViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBinding {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@SearchFragment.searchViewModel
        }

        setupKeyboardEvent()
        observeViewModel()
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

        searchViewModel.viewEvent.observe(viewLifecycleOwner, {
            it.getContentIfNotHandled()?.let { event ->
                when (event) {
                    is SearchViewEvent.ShowHistoryView -> showSearchHistoryView()
                    is SearchViewEvent.ShowEmptyView -> showEmptyView()
                    is SearchViewEvent.ShowResultView -> showResultView()
                    is SearchViewEvent.HideKeyboard -> hideKeyboard()
                }
            }
        })
    }

    private fun showEmptyView() {
        val fragment = childFragmentManager.findFragmentByTag(SearchEmptyFragment.javaClass.simpleName)

        childFragmentManager.commit {
            replace(binding.fcvSearchContainer.id, fragment ?: SearchEmptyFragment.newInstance(), SearchEmptyFragment.javaClass.simpleName)
        }
    }


    private fun showResultView() {
        childFragmentManager.commit {
            replace(binding.fcvSearchContainer.id, SearchResultFragment.newInstance())
        }
    }


    private fun showSearchHistoryView() {
        childFragmentManager.commit {
            replace(binding.fcvSearchContainer.id, SearchHistoryFragment.newInstance())
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