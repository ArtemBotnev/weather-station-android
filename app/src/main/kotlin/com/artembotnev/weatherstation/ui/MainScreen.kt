package com.artembotnev.weatherstation.ui

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artembotnev.weatherstation.MainScreenEvent
import com.artembotnev.weatherstation.MainScreenState
import com.artembotnev.weatherstation.R
import com.artembotnev.weatherstation.ui.views.MeasurePager
import com.artembotnev.weatherstation.ui.views.MeasureViewState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MainScreen(
    state: MainScreenState,
    onEvent: ((MainScreenEvent) -> Unit)? = null
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopBar(
                scrollBehavior = scrollBehavior,
                state = state,
                onEvent = onEvent,
            )
        }
    ) { innerPadding ->
        val refreshState = rememberPullToRefreshState()

        PullToRefreshBox(
            modifier = Modifier
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding),
            isRefreshing = state.isRefreshing,
            onRefresh = { onEvent?.invoke(MainScreenEvent.ReloadClicked) },
            state = refreshState,
        ) {
            MeasurePager(state = state.measuresViewStates)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    scrollBehavior: TopAppBarScrollBehavior,
    state: MainScreenState,
    onEvent: ((MainScreenEvent) -> Unit)? = null
) {
    TopAppBar(
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(stringResource(R.string.app_name))
        },
        actions = {
            IconButton(
                onClick = {
                    onEvent?.invoke(
                        MainScreenEvent.SettingsDrawerState(isOpen = !state.isSettingsDrawerOpen)
                    )
                }
            ) {
                Icon(
                    modifier = Modifier.size(32.dp),
                    imageVector = ImageVector.vectorResource(R.drawable.ic_36_settings),
                    contentDescription = stringResource(R.string.settings_icon_title),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}

@Preview
@Composable
fun MeasureViewPreview() {
    MaterialTheme {
        MainScreen(
            state = MainScreenState(
                host = "",
                port = "",
                measuresViewStates = listOf(
                    listOf(
                        MeasureViewState(
                            title = "Temperature C",
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
            )
        )
    }
}