package com.artembotnev.core.domain.entity

data class ResponseErrorException(val code: Int, override val message: String?) : Throwable()