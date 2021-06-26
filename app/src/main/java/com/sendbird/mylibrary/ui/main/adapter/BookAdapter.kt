package com.sendbird.mylibrary.ui.main.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.sendbird.mylibrary.R
import com.sendbird.mylibrary.core.binding.binding
import com.sendbird.mylibrary.data.remote.model.Book
import com.sendbird.mylibrary.databinding.ViewholderBookBinding
import com.sendbird.mylibrary.ui.main.viewholder.BookViewHolder

class BookAdapter(val showDetails: (String) -> Unit) : ListAdapter<Book, BookViewHolder>(diffUtil) {
    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = parent.binding<ViewholderBookBinding>(R.layout.viewholder_book)
        return BookViewHolder(binding).apply {
            itemView.setOnClickListener {
                showDetails(getItem(layoutPosition).isbn13)
            }
        }
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