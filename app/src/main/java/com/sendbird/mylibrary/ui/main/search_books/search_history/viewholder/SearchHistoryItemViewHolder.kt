package com.sendbird.mylibrary.ui.main.search_books.search_history.viewholder

import com.sendbird.mylibrary.core.base.BaseViewHolder
import com.sendbird.mylibrary.databinding.ViewholderSearchHistoryItemBinding
import com.sendbird.mylibrary.ui.main.search_books.search_history.data.SearchHistoryItem

class SearchHistoryItemViewHolder(
    private val binding: ViewholderSearchHistoryItemBinding
) : BaseViewHolder<ViewholderSearchHistoryItemBinding, SearchHistoryItem>(binding) {

    init {
    }

    override fun bind(data: SearchHistoryItem) {
        super.bind(data)
        with(binding) {
            this.item = data
        }
    }
}