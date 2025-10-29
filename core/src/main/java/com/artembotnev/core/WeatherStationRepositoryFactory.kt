package com.artembotnev.core

import com.artembotnev.core.data.WeatherStationRepositoryImpl
import com.artembotnev.core.domain.WeatherStationRepository
import javax.inject.Inject

class WeatherStationRepositoryFactory @Inject constructor() {

    fun get(baseUrl: String): WeatherStationRepository = WeatherStationRepositoryImpl(baseUrl)
}