package com.artembotnev.weather_station_core.data

import com.artembotnev.weather_station_core.WeatherStationClient
import com.artembotnev.weather_station_core.domain.WeatherStationRepository
import com.artembotnev.weather_station_core.domain.entity.Device
import com.artembotnev.weather_station_core.domain.entity.Measurement
import com.artembotnev.weather_station_core.domain.entity.ResponseErrorException
import io.ktor.client.call.body
import io.ktor.client.plugins.ResponseException

internal class WeatherStationRepositoryImpl(baseUrl: String) : WeatherStationRepository  {

    private val client = WeatherStationClient(baseUrl)
    private val json = kotlinx.serialization.json.Json {
        ignoreUnknownKeys = true
    }

    override suspend fun getMeasurement(deviceId: Int): Measurement? = try {
        client.getMeasurement(deviceId).body()
    } catch (e: ResponseException) {
        throw ResponseErrorException(code = e.response.status.value, message = e.message)
    }

    override suspend fun getDevices(): List<Device> = try {
        json.decodeFromString(client.getDeviseList().body())
    } catch (e: ResponseException) {
        throw ResponseErrorException(code = e.response.status.value, message = e.message)
    }

//    override suspend fun getDeviceDailyErrors(deviceId: Int): DeviceDailyErrors? {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun getDeviceListDailyErrors(): List<DeviceDailyErrors> {
//        TODO("Not yet implemented")
//    }
}