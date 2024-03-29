package com.sendbird.mylibrary.ui.main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sendbird.mylibrary.ui.main.bookmark.BookMarkFragment
import com.sendbird.mylibrary.ui.main.new_books.NewFragment
import com.sendbird.mylibrary.ui.main.search_books.SearchFragment

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> NewFragment.newInstance()
            1 -> SearchFragment.newInstance()
            else -> BookMarkFragment.newInstance()
        }
    }
}