package com.artembotnev.weatherstation.ui

import android.os.Bundle
import android.provider.CalendarContract.Colors
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.artembotnev.weatherstation.MainScreenEvent
import com.artembotnev.weatherstation.MainScreenState
import com.artembotnev.weatherstation.MainViewModel
import com.artembotnev.weatherstation.ui.theme.WeatherStationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherStationTheme {
                val viewModel: MainViewModel = hiltViewModel()
                MainContent(
                    state = viewModel.screenState,
                    onEvent = viewModel::onEvent,
                )
            }
        }
    }
}

@Composable
internal fun MainContent(state: MainScreenState, onEvent: ((MainScreenEvent) -> Unit)? = null) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val drawerScope = rememberCoroutineScope()

    LaunchedEffect(drawerState.currentValue) {
        if (drawerState.currentValue == DrawerValue.Closed) {
            // Perform actions when the drawer is closed
            println("Drawer is closed!")
            // Example: Update UI state, log an event, etc.
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        scrimColor = Color.Transparent,
        drawerContent = {
            SettingsDrawerView(
                state = state,
                onEvent = onEvent,
            )
        },
    ) {

    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    WeatherStationTheme {
//        Greeting("Android")
//    }
//}