package com.artembotnev.weather_station_core

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.appendPathSegments
import io.ktor.serialization.kotlinx.json.json
import kotlin.jvm.Throws

private const val REQUEST_TIMEOUT_MS = 3000L

class WeatherStationClient(private val baseUrl: String) {

    private val client by lazy {
        HttpClient(OkHttp) {
            expectSuccess = true
            install(HttpTimeout) {
                requestTimeoutMillis = REQUEST_TIMEOUT_MS
            }
            install(ContentNegotiation) {
                json()
            }
            install(Logging) {
                level = LogLevel.BODY
            }
        }
    }

    @Throws(ResponseException::class)
    suspend fun getDeviseList(): HttpResponse = client.get("${baseUrl}/devices")

    @Throws(ResponseException::class)
    suspend fun getMeasurement(deviceId: Int): HttpResponse = client
        .get("${baseUrl}/last_measurement") {
            url {
                appendPathSegments(deviceId.toString())
                parameters.append("additional", "true")
            }
        }
}