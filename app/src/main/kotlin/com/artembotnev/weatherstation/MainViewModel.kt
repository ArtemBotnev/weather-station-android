package com.artembotnev.weatherstation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.artembotnev.weatherstation.domain.UserPreferenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import javax.inject.Inject

@HiltViewModel
internal class MainViewModel @Inject constructor(
    val userPreference: UserPreferenceRepository
) : ViewModel() {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        // TODO: logging
    }

    var screenState by mutableStateOf(
        MainScreenState(
            title = "",
            host = "",
            port = "",
        )
    )

    fun onEvent(event: MainScreenEvent) {
        when(event) {
            is MainScreenEvent.SettingsDrawerClosed -> {  }
            is MainScreenEvent.PortInputUpdated -> {
                screenState = screenState.copy(
                    port = event.text
                )
            }
            is MainScreenEvent.HostInputUpdated -> {
                screenState = screenState.copy(
                    host = event.text
                )
            }
        }
    }
}