package com.artembotnev.weatherstation.ui.views

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artembotnev.weatherstation.R
import com.artembotnev.weatherstation.ui.theme.Typography

@Composable
internal fun MeasureView(modifier: Modifier = Modifier, state: MeasureViewState) = ElevatedCard(
    modifier = modifier.size(160.dp),
    shape = RoundedCornerShape(5.dp),
) {
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = state.place,
                    style = Typography.bodySmall,
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 2.dp),
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = state.title,
                    style = Typography.bodySmall,
                )
            }
        }
        Box(
            modifier = Modifier.fillMaxWidth().
                wrapContentHeight()
                .padding(vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = state.value,
                style = Typography.titleLarge,
            )
        }
        if (state.showDailyCalculations) {
            Row(
                modifier = Modifier.fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 2.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                DailyMeasureCell(titleRes = R.string.min, value = state.valueMin, time = state.timeMin)
                DailyMeasureCell(titleRes = R.string.average, value = state.valueAverage)
                DailyMeasureCell(titleRes = R.string.max, value = state.valueMax, time = state.timeMax)
            }
        }
    }
}

@Composable
private fun DailyMeasureCell(
    modifier: Modifier = Modifier,
    @StringRes titleRes: Int,
    value: String,
    time: String? = null,
) = Column(
    modifier = modifier.wrapContentSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.SpaceBetween
) {
    Text(
        text = time.orEmpty(),
        style = Typography.titleSmall,
    )
    Text(
        text = value,
        style = Typography.bodySmall,
    )
    Text(
        text = stringResource(titleRes),
        style = Typography.titleSmall,
    )
}

@Preview
@Composable
fun MeasureViewPreview() {
    MaterialTheme {
        MeasureView(
            state = MeasureViewState(
                title = "Temperature C",
                place = "Outdoor",
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
    }
}