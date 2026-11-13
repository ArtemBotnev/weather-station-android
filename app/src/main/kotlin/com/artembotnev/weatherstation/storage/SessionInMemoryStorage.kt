package com.artembotnev.weatherstation.storage

import com.artembotnev.core.domain.entity.Device

internal interface SessionInMemoryStorage {
    var networkAddress: String?
    var deviceLocationMap: Map<String, List<Device>>
    var currentDeviceList: List<Device>
}