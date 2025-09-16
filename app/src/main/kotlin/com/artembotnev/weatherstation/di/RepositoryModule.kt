package com.artembotnev.weatherstation.di

import com.artembotnev.weatherstation.data.UserPreferenceRepositoryImpl
import com.artembotnev.weatherstation.domain.UserPreferenceRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Binds
    abstract fun bindUserPreferenceRepository(
        userPreferenceRepositoryImpl: UserPreferenceRepositoryImpl
    ): UserPreferenceRepository
}