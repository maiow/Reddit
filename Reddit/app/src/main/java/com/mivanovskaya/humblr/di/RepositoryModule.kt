package com.mivanovskaya.humblr.di

import com.mivanovskaya.humblr.data.repository.*
import com.mivanovskaya.humblr.domain.repository.ProfileRemoteRepository
import com.mivanovskaya.humblr.domain.repository.SubredditsRemoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindProfileRemoteRepository(
        profileRemoteRepository: ProfileRemoteRepositoryImpl
    ): ProfileRemoteRepository

    @Singleton
    @Binds
    abstract fun bindSubredditsRemoteRepository(
        subredditsRemoteRepository: SubredditsRemoteRepositoryImpl
    ): SubredditsRemoteRepository
}

