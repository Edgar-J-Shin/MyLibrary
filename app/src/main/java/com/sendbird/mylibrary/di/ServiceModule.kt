package com.sendbird.mylibrary.di

import com.sendbird.mylibrary.data.remote.service.MainService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    fun provideMainService(retrofit: Retrofit): MainService =
        retrofit.create(MainService::class.java)
}