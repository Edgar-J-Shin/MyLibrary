package com.sendbird.mylibrary.core.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T : ViewDataBinding, DATA : Any> constructor(
    binding: T
) : RecyclerView.ViewHolder(binding.root) {

    open fun bind(data: DATA) {
        // 자식 클래스에서 구체화 한다.
    }
}