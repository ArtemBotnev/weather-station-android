package com.artembotnev.core.domain

import com.artembotnev.core.domain.entity.Device
import com.artembotnev.core.domain.entity.Measurement
import com.artembotnev.core.domain.entity.analytics.DeviceDailyErrors

interface WeatherStationRepository {
    suspend fun getMeasurement(deviceId: Int): Measurement?
    suspend fun getDevices(): List<Device>
    suspend fun getDeviceDailyErrors(deviceId: Int): DeviceDailyErrors?
    suspend fun getDeviceListDailyErrors(): List<DeviceDailyErrors>
}