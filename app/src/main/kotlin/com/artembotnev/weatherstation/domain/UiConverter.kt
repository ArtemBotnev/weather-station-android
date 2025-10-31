package com.artembotnev.weatherstation.domain

internal interface UiConverter<in T, out V> {
    fun convert(from: T, param: Any? = null): V
}