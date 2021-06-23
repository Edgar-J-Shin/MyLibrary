package com.sendbird.mylibrary.ui.main.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.sendbird.mylibrary.R
import com.sendbird.mylibrary.core.base.BaseViewHolder
import com.sendbird.mylibrary.core.binding.binding
import com.sendbird.mylibrary.data.remote.model.Book
import com.sendbird.mylibrary.databinding.ViewholderBookBinding
import com.sendbird.mylibrary.ui.main.viewholder.BookViewHolder
import timber.log.Timber

class BookAdapter() : ListAdapter<Book, BaseViewHolder<*, *>>(diffUtil) {
    override fun onBindViewHolder(holder: BaseViewHolder<*, *>, position: Int) {
        (holder as BookViewHolder).bind(getItem(position))
        Timber.e("sjh bind $position ${getItem(position)}")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*, *> {
        val binding = parent.binding<ViewholderBookBinding>(R.layout.viewholder_book)
        return BookViewHolder(binding)
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<Book>() {

            override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem.isbn13 == newItem.isbn13
            }
        }
    }
}