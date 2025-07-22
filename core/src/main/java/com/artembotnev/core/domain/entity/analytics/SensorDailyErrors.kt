package com.artembotnev.core.domain.entity.analytics

import kotlinx.serialization.Serializable

@Serializable
data class SensorDailyErrors(
    val sensorId: String,
    val sensorName: String?,
    val totalMeasures: Int,
    val errorCount: Int,
    val errorPercent: Double,
)