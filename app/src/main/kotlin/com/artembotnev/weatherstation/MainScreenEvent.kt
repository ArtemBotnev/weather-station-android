package com.artembotnev.weatherstation

internal sealed interface MainScreenEvent {

    data object SettingsDrawerClosed : MainScreenEvent
    data object ReloadClicked : MainScreenEvent
    data class HostInputUpdated(val text: String) : MainScreenEvent
    data class PortInputUpdated(val text: String) : MainScreenEvent
}