package com.artembotnev.weatherstation

import androidx.compose.runtime.Immutable

@Immutable
internal data class MainScreenState(
    val title: String,
    val host: String,
    val port: String,
)