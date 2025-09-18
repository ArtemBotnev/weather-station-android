package com.artembotnev.weatherstation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artembotnev.weatherstation.di.IoDispatcher
import com.artembotnev.weatherstation.domain.UserPreferenceRepository
import com.artembotnev.weatherstation.storage.SessionInMemoryStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import timber.log.Timber
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

private const val TAG = "MainViewModel"

@HiltViewModel
internal class MainViewModel @Inject constructor(
    private val userPreference: UserPreferenceRepository,
    private val inMemoryStorage: SessionInMemoryStorage,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val keyboardDebounceDelay = 2.seconds

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        Timber.tag(TAG).e(exception.message.orEmpty())
    }
    private val mutex = Mutex()

    private val _uiState = MutableStateFlow(createInitialState())
    val uiState = _uiState.asStateFlow()

    init {
        fillState()
        observeNetworkAddress()
        observeHostAndPortChanged()
    }

    fun onEvent(event: MainScreenEvent) {
        when(event) {
            is MainScreenEvent.SettingsDrawerClosed -> {  }
            is MainScreenEvent.PortInputUpdated -> {
                _uiState.update {
                    it.copy(port = event.text)
                }
            }
            is MainScreenEvent.HostInputUpdated -> {
                _uiState.update {
                    it.copy(host = event.text)
                }
            }
        }
    }

    @OptIn(FlowPreview::class)
    private fun observeHostAndPortChanged() {
        viewModelScope.launch(ioDispatcher + coroutineExceptionHandler) {
            uiState.map { it.host }
                .debounce(keyboardDebounceDelay)
                .collect { userPreference.setHost(uiState.value.host) }
        }
        viewModelScope.launch(ioDispatcher + coroutineExceptionHandler) {
            uiState.map { it.port }
                .debounce(keyboardDebounceDelay)
                .collect { userPreference.setPort(uiState.value.port) }
        }
    }

    private fun observeNetworkAddress() {
        viewModelScope.launch(ioDispatcher + coroutineExceptionHandler) {
            userPreference.hostFlow()
                .zip(userPreference.portFlow()) { host, port ->
                    if (host.isBlank()) return@zip ""
                    if (port.isBlank()) host else "${host}:${port}"
                }
                .collect {
                    mutex.withLock {
                        inMemoryStorage.networkAddress = it
                        Timber.tag("$TAG networkAddress").i(it)
                    }
                }
        }
    }

    private fun createInitialState(): MainScreenState = MainScreenState(
        host = "",
        port = "",
    )

    private fun fillState() {
        viewModelScope.launch(ioDispatcher + coroutineExceptionHandler) {
            val host = userPreference.getHost()
            val port = userPreference.getPort()
            Timber.tag("$TAG host:").i(host)
            Timber.tag("$TAG port:").i(port)
            _uiState.update {
                it.copy(host = host, port = port)
            }
        }
    }
}