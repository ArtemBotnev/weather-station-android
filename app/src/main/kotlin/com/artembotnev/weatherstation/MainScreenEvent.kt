package com.artembotnev.weatherstation

internal sealed interface MainScreenEvent {

    data object JustEvent : MainScreenEvent
}