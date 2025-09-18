package com.artembotnev.weatherstation.storage

import javax.inject.Inject

internal class SessionInMemoryStorageImpl @Inject constructor() : SessionInMemoryStorage {
    override var networkAddress: String? = null
}