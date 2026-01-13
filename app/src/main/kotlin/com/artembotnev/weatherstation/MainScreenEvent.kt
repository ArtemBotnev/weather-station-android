package com.artembotnev.weatherstation

internal sealed interface MainScreenEvent {

    data class SettingsDrawerState(val isOpen: Boolean) : MainScreenEvent
    data object ReloadClicked : MainScreenEvent
    data class HostInputUpdated(val text: String) : MainScreenEvent
    data class PortInputUpdated(val text: String) : MainScreenEvent
}