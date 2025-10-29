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
internal fun MeasureView(state: MeasureViewState) = ElevatedCard(
    modifier = Modifier.size(150.dp),
    shape = RoundedCornerShape(5.dp),
) {
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                text = state.title,
                style = Typography.bodySmall,
            )
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
                DailyMeasureCell(titleRes = R.string.min, value = state.valueMin)
                DailyMeasureCell(titleRes = R.string.average, value = state.valueAverage)
                DailyMeasureCell(titleRes = R.string.max, value = state.valueMax)
            }
        }
    }
}

@Composable
private fun DailyMeasureCell(
    modifier: Modifier = Modifier,
    @StringRes titleRes: Int,
    value: String,
) = Column(
    modifier = modifier.wrapContentSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.SpaceBetween
) {
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
                value = "21",
                valueMin = "15",
                valueMax = "23",
                valueAverage = "19",
                showDailyCalculations = true
            )
        )
    }
}