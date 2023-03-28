package com.mivanovskaya.humblr.di

import com.mivanovskaya.humblr.data.repository.*
import com.mivanovskaya.humblr.data.sharedprefsservice.SharedPrefsService
import com.mivanovskaya.humblr.domain.storageservice.StorageService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class StorageServiceModule  {

    @Singleton
    @Binds
    abstract fun bindStorageService(
        storageService: SharedPrefsService
    ): StorageService
}