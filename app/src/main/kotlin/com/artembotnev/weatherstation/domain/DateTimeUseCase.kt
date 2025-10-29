package com.artembotnev.weatherstation.domain

import org.joda.time.LocalDateTime
import org.joda.time.format.DateTimeFormat
import timber.log.Timber
import javax.inject.Inject

private const val TIME_STAMP_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ"
private const val TIME_PATTERN = "HH:mm"

internal class DateTimeUseCase @Inject constructor() {

    fun timeStampToTime(stamp: String): String = try {
        LocalDateTime.parse(
            stamp,
            DateTimeFormat.forPattern(TIME_STAMP_PATTERN)
        ).toString(TIME_PATTERN)
    } catch (t: Throwable) {
        Timber.e(t)
        "-"
    }
}