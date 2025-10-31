package com.artembotnev.weatherstation.domain

import com.artembotnev.core.domain.WeatherStationRepository
import com.artembotnev.core.domain.entity.Device
import com.artembotnev.core.domain.entity.Measurement
import javax.inject.Inject

internal class MeasurementUseCase @Inject constructor() {

    /**
     * Returns map of devices as a key and it measurement as a value
     *
     * @param repository - repository of weather data
     * @param deviceList - list of devices
     *
     * @return map of devices as a key and it measurement as a value
     */
    suspend fun getLocationMeasurementMap(
        repository: WeatherStationRepository,
        deviceList: List<Device>,
    ): Map<Device, Measurement?> {
        return deviceList
            .associateBy(
                { it }, { repository.getMeasurement(it.id) }
            )
    }
}