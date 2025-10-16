package com.artembotnev.weatherstation.ui.views

import androidx.compose.runtime.Immutable

@Immutable
internal data class MeasureViewState(
    val title: String,
    val value: String,
    val valueMin: String,
    val valueAverage: String,
    val valueMax: String,
    val showDailyCalculations: Boolean,
)