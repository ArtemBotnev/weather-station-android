package com.artembotnev.weatherstation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
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
                MainContent()
            }
        }
    }
}

@Composable
fun MainContent() {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val drawerScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {

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