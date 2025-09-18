package com.artembotnev.weatherstation.di

import com.artembotnev.weatherstation.storage.SessionInMemoryStorage
import com.artembotnev.weatherstation.storage.SessionInMemoryStorageImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class StorageModule {

    @Binds
    @Singleton
    abstract fun bindSessionInMemoryStorage(
        sessionInMemoryStorageImpl: SessionInMemoryStorageImpl
    ): SessionInMemoryStorage
}