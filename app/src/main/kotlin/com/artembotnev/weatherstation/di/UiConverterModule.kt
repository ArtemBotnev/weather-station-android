package com.artembotnev.weatherstation.di

import com.artembotnev.weatherstation.domain.DateTimeUseCase
import com.artembotnev.weatherstation.ui.converters.MeasurementUiConverter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class UiConverterModule {

    @Provides
    @Singleton
    fun providesMeasurementUiConverter(
        dateTime: DateTimeUseCase
    ): MeasurementUiConverter = MeasurementUiConverter(dateTime)
}