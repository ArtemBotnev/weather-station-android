package com.artembotnev.core

import com.artembotnev.core.data.WeatherStationRepositoryImpl
import com.artembotnev.core.domain.WeatherStationRepository
import javax.inject.Inject

class WeatherStationRepositoryFactory @Inject constructor(private val baseUrl: String) {

    fun get(): WeatherStationRepository = WeatherStationRepositoryImpl(baseUrl)

    fun recreate(baseUrl: String): WeatherStationRepository = WeatherStationRepositoryImpl(baseUrl)
}