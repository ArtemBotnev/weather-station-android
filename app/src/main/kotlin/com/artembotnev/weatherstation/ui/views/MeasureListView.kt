package com.artembotnev.weatherstation.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun MeasureListView(states: List<MeasureViewState>) {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        states.forEach {
            MeasureView(
                modifier = Modifier.padding(top = 8.dp),
                state = it
            )
        }
    }
}