package com.artembotnev.weather_station_core.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class Measurement(
    val timestamp: String? = null,
    val timeZoneHours: Int,
    val device: Device,
    val measures: List<Measure>,
)
