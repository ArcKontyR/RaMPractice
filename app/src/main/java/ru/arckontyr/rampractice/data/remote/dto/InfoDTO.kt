package ru.arckontyr.rampractice.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class InfoDTO(
    val count: Int,
    val pages: Int,
    val next: String? = null,
    val prev: String? = null
)