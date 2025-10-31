package com.artembotnev.weatherstation.storage

import com.artembotnev.core.domain.entity.Device
import javax.inject.Inject

internal class SessionInMemoryStorageImpl @Inject constructor() : SessionInMemoryStorage {
    override var networkAddress: String? = null
    override var currentDeviceList: List<Device> = emptyList()
}