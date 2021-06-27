package com.sendbird.mylibrary.di

import com.sendbird.mylibrary.repository.BookmarkRepository
import com.sendbird.mylibrary.repository.BookmarkRepositoryImpl
import com.sendbird.mylibrary.repository.SearchRepository
import com.sendbird.mylibrary.repository.SearchRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindSearchRepository(searchRepositoryImpl: SearchRepositoryImpl): SearchRepository

    @Binds
    abstract fun bindBookmarkRepository(bookmarkRepositoryImpl: BookmarkRepositoryImpl): BookmarkRepository
}