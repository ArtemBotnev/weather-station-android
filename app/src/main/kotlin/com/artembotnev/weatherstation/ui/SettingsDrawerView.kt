package com.artembotnev.weatherstation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artembotnev.weatherstation.MainScreenEvent
import com.artembotnev.weatherstation.MainScreenState
import com.artembotnev.weatherstation.R

private const val DRAWER_ELEVATION = 4

@Composable
internal fun SettingsDrawerView(
    state: MainScreenState,
    onEvent: ((MainScreenEvent) -> Unit)? = null
) {
    Surface(
        shadowElevation = DRAWER_ELEVATION.dp,
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding()
            .background(MaterialTheme.colorScheme.onBackground)
            .wrapContentWidth()
    ) {
        Card(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(fraction = .8f)
                .pointerInput(Unit) {
                    detectHorizontalDragGestures { _, dragAmount ->
                        if (dragAmount < 0) {
                            onEvent?.invoke(
                                MainScreenEvent.SettingsDrawerState(isOpen = !state.isSettingsDrawerOpen)
                            )
                        }
                    }
                },
            shape = RoundedCornerShape(4.dp)
        ) {
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .wrapContentWidth()
                    .fillMaxHeight()
                    .padding(8.dp)
            ) {
                Text(
                    text = stringResource(R.string.settings_title),
                    style = MaterialTheme.typography.titleLarge
                )
                HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
                OutlinedTextField(
                    modifier = Modifier,
                    value = state.host,
                    singleLine = true,
                    label = { Text(stringResource(R.string.host_title)) },
                    placeholder = { Text(state.host) },
                    onValueChange = {
                        onEvent?.invoke(
                            MainScreenEvent.HostInputUpdated(it)
                        )
                    }
                )
                OutlinedTextField(
                    modifier = Modifier.padding(top = 4.dp),
                    value = state.port,
                    singleLine = true,
                    label = { Text(stringResource(R.string.port_title)) },
                    placeholder = { Text(state.port) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    onValueChange = {
                        onEvent?.invoke(
                            MainScreenEvent.PortInputUpdated(it)
                        )
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun SettingsDrawerViewPreview() {
//    MaterialTheme {
//        SettingsDrawerView(
//            state = MainScreenState(
//                title = "title"
//            )
//        )
//    }
}