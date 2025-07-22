package com.artembotnev.weather_station_core.domain.entity.analytics

data class DeviceDailyErrors(
    val deviceId: Int,
    val deviceName: String?,
    val startAnalysisTime: String? = null,
    val lastUpdateAnalysisTime: String? = null,
    val analysisDuration: String? = null,
    val timeZoneHours: Int = 0,
    val sensorErrorList: List<SensorDailyErrors>,
)