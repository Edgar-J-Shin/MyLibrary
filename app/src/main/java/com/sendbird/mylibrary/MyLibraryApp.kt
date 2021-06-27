package com.sendbird.mylibrary

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyLibraryApp : Application() {

    companion object {
        private val recentlyKeywords: MutableList<String> = mutableListOf()

        fun insertKeyword(keyword: String) {
            recentlyKeywords.add(keyword)
        }

        fun deleteKeyword(keyword: String) {
            recentlyKeywords.remove(keyword)
        }

        fun clearKeywordAll() {
            recentlyKeywords.clear()
        }

        fun getKeywordAll(): List<String> = recentlyKeywords.reversed()
    }
}