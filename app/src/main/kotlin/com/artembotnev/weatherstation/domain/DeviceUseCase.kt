package com.artembotnev.weatherstation.domain

import com.artembotnev.core.domain.WeatherStationRepository
import com.artembotnev.core.domain.entity.Device
import javax.inject.Inject

private const val OTHER_DEVICE_LOCATION = "other"

internal class DeviceUseCase @Inject constructor() {

    suspend fun getDeviceLocationMap(repository: WeatherStationRepository): Map<String, List<Device>> {
        return repository.getDevices()
            .map { if (it.location == null) it.copy(location = OTHER_DEVICE_LOCATION) else it }
            .groupBy { it.location ?: OTHER_DEVICE_LOCATION }
    }
}