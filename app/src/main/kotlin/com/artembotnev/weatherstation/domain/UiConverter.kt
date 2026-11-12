package com.artembotnev.weatherstation.domain

internal interface UiConverter<in T, out V> {
    fun convert(from: T, param1: Any? = null, param2: Any? = null): V
}