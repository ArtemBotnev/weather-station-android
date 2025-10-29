package com.artembotnev.weatherstation

internal fun String?.orDash(): String = this ?: "-"

internal fun Double?.toStringOrDash() = this?.toString().orDash()