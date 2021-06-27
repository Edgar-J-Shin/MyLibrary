package com.sendbird.mylibrary.ui.main.bookmark

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.sendbird.mylibrary.R
import com.sendbird.mylibrary.core.base.BaseFragment
import com.sendbird.mylibrary.databinding.FragmentBookMarkBinding
import com.sendbird.mylibrary.repository.FILTER_TYPE
import com.sendbird.mylibrary.ui.detail.DetailBookActivity
import com.sendbird.mylibrary.ui.main.adapter.BookAdapter
import com.sendbird.mylibrary.ui.view.showBottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookMarkFragment : BaseFragment<FragmentBookMarkBinding>(R.layout.fragment_book_mark) {

    private val bookmarkViewModel by viewModels<BookMarkViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBinding {
            lifecycleOwner = viewLifecycleOwner
            viewModel = bookmarkViewModel
        }

        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        binding.rvBookmark.apply {
            adapter = BookAdapter(showDetails = { isbn13 ->
                startActivity(Intent(context, DetailBookActivity::class.java).apply {
                    putExtra(DetailBookActivity.INTENT_EXTRA_ISBN13, isbn13)
                })
            })
            setHasFixedSize(true)
        }
    }

    private fun observeViewModel() {
        // 필터 뷰
        bookmarkViewModel.viewEvent.observe(viewLifecycleOwner, {
            it.getContentIfNotHandled()?.let { event ->
                when (event) {
                    is BookmarkViewEvent.ShowFilterView -> showFilterView()
                }
            }
        })

        // 필터 타입 변경
        bookmarkViewModel.filterType.observe(viewLifecycleOwner, {
            binding.tvBookmarkFilter.text = when (it) {
                FILTER_TYPE.ALPHABET_ASC -> getString(R.string.filter_alphabet_asc)
                FILTER_TYPE.ALPHABET_DESC -> getString(R.string.filter_alphabet_desc)
                FILTER_TYPE.PRICE_ASC -> getString(R.string.filter_price_asc)
                FILTER_TYPE.PRICE_DESC -> getString(R.string.filter_price_desc)
                FILTER_TYPE.NONE -> ""
            }

            bookmarkViewModel.getBookmarkAll(it)
        })

        bookmarkViewModel.books.observe(viewLifecycleOwner, {
            (binding.rvBookmark.adapter as BookAdapter).submitList(it)
        })

    }

    private fun showFilterView() {
        if (binding.tvBookmarkFilter.text.equals("")) {
            bookmarkViewModel.setFilterType(FILTER_TYPE.ALPHABET_ASC)
        } else {
            activity?.showBottomSheetDialog {
                addTextView(getString(R.string.filter_alphabet_asc)) {
                    bookmarkViewModel.setFilterType(FILTER_TYPE.ALPHABET_ASC)
                }
                addTextView(getString(R.string.filter_alphabet_desc)) {
                    bookmarkViewModel.setFilterType(FILTER_TYPE.ALPHABET_DESC)
                }
                addTextView(getString(R.string.filter_price_asc)) {
                    bookmarkViewModel.setFilterType(FILTER_TYPE.PRICE_ASC)
                }
                addTextView(getString(R.string.filter_price_desc)) {
                    bookmarkViewModel.setFilterType(FILTER_TYPE.PRICE_DESC)
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = BookMarkFragment()
    }
}