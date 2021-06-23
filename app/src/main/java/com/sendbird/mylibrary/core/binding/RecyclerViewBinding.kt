package com.sendbird.mylibrary.core.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

object RecyclerViewBinding {

//    @JvmStatic
//    @BindingAdapter("adapter")
//    fun bindAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>?) {
//        view.adapter = adapter?.apply {
//            stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
//        }
//    }

    @Suppress("UNCHECKED_CAST")
    @JvmStatic
    @BindingAdapter("bind:submitList")
    fun bindSubmitList(view: RecyclerView, list: List<Any>?) {
        (view.adapter as? ListAdapter<Any, *>)?.submitList(list)
    }
//
//    @JvmStatic
//    @BindingAdapter(value = ["loadMore_listener", "loadMore_threshold"], requireAll = false)
//    fun bindLoadMore(view: RecyclerView, loadMoreListener: OnLoadMoreListener?, threshold: Int?) {
//        loadMoreListener?.apply {
//            threshold?.let {
//                this.threshold = it
//            }
//        }?.addTo(view)
//    }
}