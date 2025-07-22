package com.artembotnev.core.data

import com.artembotnev.core.WeatherStationClient
import com.artembotnev.core.domain.WeatherStationRepository
import com.artembotnev.core.domain.entity.Device
import com.artembotnev.core.domain.entity.Measurement
import com.artembotnev.core.domain.entity.ResponseErrorException
import com.artembotnev.core.domain.entity.analytics.DeviceDailyErrors
import io.ktor.client.call.body
import io.ktor.client.plugins.ResponseException

internal class WeatherStationRepositoryImpl(baseUrl: String) : WeatherStationRepository  {

    private val client = WeatherStationClient(baseUrl)
    private val json = kotlinx.serialization.json.Json {
        ignoreUnknownKeys = true
    }

    override suspend fun getMeasurement(deviceId: Int): Measurement? = try {
        json.decodeFromString(client.getMeasurement(deviceId).body())
    } catch (e: ResponseException) {
        throw ResponseErrorException(code = e.response.status.value, message = e.message)
    }

    override suspend fun getDevices(): List<Device> = try {
        json.decodeFromString(client.getDeviseList().body())
    } catch (e: ResponseException) {
        throw ResponseErrorException(code = e.response.status.value, message = e.message)
    }

    override suspend fun getDeviceDailyErrors(deviceId: Int): DeviceDailyErrors? = try {
        json.decodeFromString(client.getDeviceDailyErrors(deviceId).body())
    } catch (e: ResponseException) {
        throw ResponseErrorException(code = e.response.status.value, message = e.message)
    }

    override suspend fun getDeviceListDailyErrors(): List<DeviceDailyErrors> = try {
        json.decodeFromString(client.getDeviceListDailyErrors().body())
    } catch (e: ResponseException) {
        throw ResponseErrorException(code = e.response.status.value, message = e.message)
    }
}