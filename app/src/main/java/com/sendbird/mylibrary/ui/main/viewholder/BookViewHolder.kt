package com.sendbird.mylibrary.ui.main.viewholder

import com.sendbird.mylibrary.core.base.BaseViewHolder
import com.sendbird.mylibrary.model.Book
import com.sendbird.mylibrary.databinding.ViewholderBookBinding

class BookViewHolder(private val binding: ViewholderBookBinding) : BaseViewHolder<ViewholderBookBinding, Book>(binding) {
    
    override fun bind(data: Book) {
        super.bind(data)

        binding.run {
            book = data
            viewHolder = this@BookViewHolder

            executePendingBindings()
        }
    }
}