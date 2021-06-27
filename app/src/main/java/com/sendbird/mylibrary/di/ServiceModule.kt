package com.sendbird.mylibrary.di

import com.sendbird.mylibrary.data.remote.service.SearchService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    fun provideMainService(retrofit: Retrofit): SearchService =
        retrofit.create(SearchService::class.java)
}