package com.artembotnev.weatherstation.di

import com.artembotnev.core.WeatherStationRepositoryFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class FactoryModule {

    @Provides
    @Singleton
    fun providesWeatherStationFactory(): WeatherStationRepositoryFactory =
        WeatherStationRepositoryFactory()
}