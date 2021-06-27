package com.sendbird.mylibrary.ui.main.search_books.search_history.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.sendbird.mylibrary.R
import com.sendbird.mylibrary.core.binding.binding
import com.sendbird.mylibrary.databinding.ViewholderSearchHistoryItemBinding
import com.sendbird.mylibrary.model.SearchHistoryItem
import com.sendbird.mylibrary.ui.main.search_books.search_history.viewholder.SearchHistoryItemViewHolder

class SearchHistoryAdapter(
    val searchKeyword: (String) -> Unit,
    val deleteKeyword: (String) -> Unit
) : ListAdapter<SearchHistoryItem, SearchHistoryItemViewHolder>(diffUtil) {
    override fun onBindViewHolder(holder: SearchHistoryItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHistoryItemViewHolder {
        val binding = parent.binding<ViewholderSearchHistoryItemBinding>(R.layout.viewholder_search_history_item)
        return SearchHistoryItemViewHolder(binding).apply {
            // 아이템 선택 시 동작 : 선택된 키워드로 검색
            itemView.setOnClickListener {
                searchKeyword(getItem(layoutPosition).name)
            }

            // 삭제 버튼 선택 시 동작 : 해당 아이템 삭제
            binding.ivSearchHistoryItemRemove.setOnClickListener {
                deleteKeyword(getItem(layoutPosition).name)
            }
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<SearchHistoryItem>() {

            override fun areItemsTheSame(oldItem: SearchHistoryItem, newItem: SearchHistoryItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: SearchHistoryItem, newItem: SearchHistoryItem): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }
}