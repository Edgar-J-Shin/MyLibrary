package com.sendbird.mylibrary.di

import android.content.Context
import android.content.SharedPreferences
import com.sendbird.mylibrary.data.local.MyLibraryDatabase
import com.sendbird.mylibrary.data.local.dao.BookmarkDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences("${context.packageName}_preferences", Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) : MyLibraryDatabase =
        MyLibraryDatabase.create(context)

    @Provides
    @Singleton
    fun provideBookmarkDao(myLibraryDatabase: MyLibraryDatabase) : BookmarkDao =
        myLibraryDatabase.bookmarkDao()
}