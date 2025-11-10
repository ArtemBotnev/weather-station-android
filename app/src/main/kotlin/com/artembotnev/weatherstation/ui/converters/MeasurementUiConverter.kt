package com.artembotnev.weatherstation.ui.converters

import com.artembotnev.core.domain.entity.Measurement
import com.artembotnev.weatherstation.domain.DateTimeUseCase
import com.artembotnev.weatherstation.domain.UiConverter
import com.artembotnev.weatherstation.orDash
import com.artembotnev.weatherstation.toStringOrDash
import com.artembotnev.weatherstation.ui.views.MeasureViewState
import javax.inject.Inject

internal class MeasurementUiConverter @Inject constructor(
    private val dateTime: DateTimeUseCase,
) : UiConverter<Measurement, List<MeasureViewState>> {

    override fun convert(from: Measurement, param: Any?): List<MeasureViewState> =
        from.measures.map {
            MeasureViewState(
                title = "${it.measureName} ${it.measureUnit}",
                value = it.measureValue.toString(),
                time = from.timestamp.orDash(),
                valueMin = it.dailyCalculation?.minValue.toStringOrDash(),
                valueAverage = it.dailyCalculation?.averageValue.toStringOrDash(),
                valueMax = it.dailyCalculation?.maxValue.toStringOrDash(),
                timeMin = it.dailyCalculation?.minValueTime?.let { stamp ->
                    dateTime.timeStampToTime(stamp)
                }.orDash(),
                timeMax = it.dailyCalculation?.maxValueTime?.let { stamp ->
                    dateTime.timeStampToTime(stamp)
                }.orDash(),
                sensorName = it.sensorName,
                sensorPlace = it.sensorPlace,
                showDailyCalculations = param?.let { value ->
                    value as? Boolean ?: true
                } ?: true
            )
        }
}