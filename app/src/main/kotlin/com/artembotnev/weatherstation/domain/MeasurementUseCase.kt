package com.artembotnev.weatherstation.domain

import com.artembotnev.core.domain.WeatherStationRepository
import com.artembotnev.core.domain.entity.Device
import com.artembotnev.core.domain.entity.Measurement
import javax.inject.Inject

internal class MeasurementUseCase @Inject constructor() {

    /**
     * Returns map of devices as a key and it measurement as a value
     *
     * @param deviceList - list of devices
     * @param getMeasurement - lambda to get measurement
     *
     * @return map of devices as a key and it measurement as a value
     */
    inline fun getLocationMeasurementMap(
        deviceList: List<Device>,
        getMeasurement: (Int) -> Measurement?
    ): Map<Device, Measurement?> {
        return deviceList
            .associateBy(
                { it }, { getMeasurement(it.id) }
            )
    }
}