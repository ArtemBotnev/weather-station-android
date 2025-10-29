package com.artembotnev.weatherstation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artembotnev.core.WeatherStationRepositoryFactory
import com.artembotnev.core.domain.WeatherStationRepository
import com.artembotnev.weatherstation.di.IoDispatcher
import com.artembotnev.weatherstation.domain.DateTimeUseCase
import com.artembotnev.weatherstation.domain.UserPreferenceRepository
import com.artembotnev.weatherstation.storage.SessionInMemoryStorage
import com.artembotnev.weatherstation.ui.views.MeasureViewState
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
    private val weatherRepositoryFactory: WeatherStationRepositoryFactory,
    private val dateTime: DateTimeUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val keyboardDebounceDelay = 2.seconds

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        Timber.tag(TAG).e(exception.message.orEmpty())
    }
    private val mutex = Mutex()

    private val _uiState = MutableStateFlow(createInitialState())
    val uiState = _uiState.asStateFlow()

    private var _weatherRepository: WeatherStationRepository? = null

    init {
        fillState()
        observeNetworkAddress()
        observeHostAndPortChanged()
        loadData()
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
            is MainScreenEvent.ReloadClicked -> loadData()
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
                        _weatherRepository = weatherRepositoryFactory.get("http://${it}")
                    }
                    loadData()
                }
        }
    }

    private fun createInitialState(): MainScreenState = MainScreenState(
        host = "",
        port = "",
        measureViewState = MeasureViewState(
            title = "-",
            value = "-",
            valueMin = "-",
            valueAverage = "-",
            valueMax = "-",
            timeMin = "-",
            timeMax = "-",
            showDailyCalculations = true,
        )
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

    private fun loadData() {
        viewModelScope.launch(ioDispatcher + coroutineExceptionHandler) {
            _uiState.update { it.copy(isRefreshing = true) }
            _weatherRepository?.getMeasurement(0)?.measures[0]?.let { measure ->
                _uiState.update {
                    it.copy(
                        measureViewState = it.measureViewState.copy(
                            title = "${measure.measureName} ${measure.measureUnit}",
                            value = measure.measureValue.toString(),
                            valueMin = measure.dailyCalculation?.minValue.toStringOrDash(),
                            valueAverage = measure.dailyCalculation?.averageValue.toStringOrDash(),
                            valueMax = measure.dailyCalculation?.maxValue.toStringOrDash(),
                            timeMin = measure.dailyCalculation?.minValueTime?.let { stamp ->
                                dateTime.timeStampToTime(stamp)
                            }.orDash(),
                            timeMax = measure.dailyCalculation?.maxValueTime?.let { stamp ->
                                dateTime.timeStampToTime(stamp)
                            }.orDash()
                        ),
                        isRefreshing = false
                    )
                }
            }
        }
    }
}