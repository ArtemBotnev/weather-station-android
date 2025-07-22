package com.artembotnev.weather_station_core.domain

import com.artembotnev.weather_station_core.domain.entity.Device
import com.artembotnev.weather_station_core.domain.entity.Measurement

interface WeatherStationRepository {
    suspend fun getMeasurement(deviceId: Int): Measurement?
    suspend fun getDevices(): List<Device>
//    suspend fun getDeviceDailyErrors(deviceId: Int): DeviceDailyErrors?
//    suspend fun getDeviceListDailyErrors(): List<DeviceDailyErrors>
}