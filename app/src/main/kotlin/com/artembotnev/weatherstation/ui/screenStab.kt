package com.artembotnev.weatherstation.ui

import com.artembotnev.weatherstation.DeviceUiState
import com.artembotnev.weatherstation.MainScreenState
import com.artembotnev.weatherstation.ui.views.MeasureViewState

internal val screenStab = MainScreenState(
    host = "",
    port = "",
    measuresViewStates = listOf(
        listOf(
            MeasureViewState(
                title = "Temperature C",
                place = "Outdoor",
                value = "21",
                time = "21.10.25 16:43",
                valueMin = "15",
                valueMax = "23",
                valueAverage = "19",
                timeMin = "14:34",
                timeMax = "09:28",
                sensorName = "sensor",
                sensorPlace = "place",
                showDailyCalculations = true
            )
        )
    ),
    locations = listOf("Location_1", "Location_2"),
    selectedLocation = "Location_1",
    deviceLocations = mapOf(
        "Location_1" to listOf(
            DeviceUiState(
                id = 1,
                name = "Device_1"
            ),
            DeviceUiState(
                id = 2,
                name = "Device_2",
            )
        ),
        "Location_2" to listOf(
            DeviceUiState(
                id = 3,
                name = "Device_3"
            )
        )
    ),
    selectedDevice = DeviceUiState(
        id = 2,
        name = "Device_2",
    )
)