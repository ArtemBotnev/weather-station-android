package com.artembotnev.weather_station_core

import com.artembotnev.weather_station_core.data.WeatherStationRepositoryImpl
import com.artembotnev.weather_station_core.domain.WeatherStationRepository
import javax.inject.Inject

class WeatherStationRepositoryFactory @Inject constructor(private val baseUrl: String) {

    fun get(): WeatherStationRepository = WeatherStationRepositoryImpl(baseUrl)

    fun recreate(baseUrl: String): WeatherStationRepository = WeatherStationRepositoryImpl(baseUrl)
}