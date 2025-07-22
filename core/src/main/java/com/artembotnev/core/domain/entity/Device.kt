package com.artembotnev.core.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class Device(
    val id: Int,
    val type: String?,
    val name: String?,
    val location: String?,
)
