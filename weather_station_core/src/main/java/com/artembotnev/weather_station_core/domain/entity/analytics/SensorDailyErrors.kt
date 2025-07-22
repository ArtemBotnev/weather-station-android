package com.artembotnev.weather_station_core.domain.entity.analytics

data class SensorDailyErrors(
    val sensorId: String,
    val sensorName: String?,
    val totalMeasures: Int,
    val errorCount: Int,
    val errorPercent: Double,
)