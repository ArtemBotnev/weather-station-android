package com.artembotnev.weatherstation.domain

import kotlinx.coroutines.flow.Flow

internal interface UserPreferenceRepository {
    suspend fun setHost(host: String)
    suspend fun setPort(port: String)
    suspend fun getHost(): String
    suspend fun getPort(): String
    suspend fun hostFlow(): Flow<String>
    suspend fun portFlow(): Flow<String>
}