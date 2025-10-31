package com.artembotnev.weatherstation.ui.views

import androidx.compose.runtime.Immutable

@Immutable
internal data class MeasureViewState(
    val title: String,
    val value: String,
    val time: String,
    val valueMin: String,
    val valueAverage: String,
    val valueMax: String,
    val timeMin: String,
    val timeMax: String,
    val showDailyCalculations: Boolean,
)