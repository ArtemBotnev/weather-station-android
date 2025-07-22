package com.artembotnev.core.domain.entity.analytics

import kotlinx.serialization.Serializable

@Serializable
data class DeviceDailyErrors(
    val deviceId: Int,
    val deviceName: String?,
    val startAnalysisTime: String? = null,
    val lastUpdateAnalysisTime: String? = null,
    val analysisDuration: String? = null,
    val timeZoneHours: Int = 0,
    val sensorErrorList: List<SensorDailyErrors>,
)