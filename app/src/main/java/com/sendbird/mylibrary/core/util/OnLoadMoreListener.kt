package com.sendbird.mylibrary.core.util

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class OnLoadMoreListener(
    private val onLoadMore: (Int) -> Unit
) : RecyclerView.OnScrollListener() {

    private val PAGE_THRESHOLD: Int = 5
    private val PAGE_SIZE: Int = 10

    private var page: Int = 1
    private var isLoading: Boolean = false

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        recyclerView.layoutManager?.let { layoutManager ->
            if (dy <= 0) return

            val totalItemCount = layoutManager.itemCount
            val lastVisibleItem = (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()

            // 로딩중이거나 마지막 페이지이면 동작하지 않는다
            if (isLoading || totalItemCount < PAGE_SIZE * page) return

            if (totalItemCount <= lastVisibleItem + PAGE_THRESHOLD) {
                onLoadMore(++page)
                isLoading = true
            }
        }
    }

    fun resetLoading() {
        isLoading = false
    }
}