package com.artembotnev.weatherstation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import javax.inject.Inject

@HiltViewModel
internal class MainViewModel @Inject constructor() : ViewModel() {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        // TODO: logging
    }

    var screenState by mutableStateOf(MainScreenState(title = ""))

    fun onEvent(event: MainScreenEvent) {
        when(event) {
            is MainScreenEvent.JustEvent -> {  }
        }
    }
}