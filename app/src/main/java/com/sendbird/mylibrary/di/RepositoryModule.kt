package com.sendbird.mylibrary.di

import com.sendbird.mylibrary.repository.MainRepository
import com.sendbird.mylibrary.repository.MainRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMainRepository(mainRepositoryImpl: MainRepositoryImpl): MainRepository
}