package com.artembotnev.weatherstation

import androidx.compose.runtime.Immutable
import com.artembotnev.core.domain.entity.Device
import com.artembotnev.weatherstation.ui.views.MeasureViewState

@Immutable
internal data class MainScreenState(
    val host: String,
    val port: String,
    val measureViewState: MeasureViewState,
    val isRefreshing: Boolean = false,
)