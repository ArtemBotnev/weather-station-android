package com.artembotnev.weather_station_core.domain.entity

data class ResponseErrorException(val code: Int, override val message: String?) : Throwable()