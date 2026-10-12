package ru.arckontyr.rampractice.domain.model

import ru.arckontyr.rampractice.data.enums.Gender
import ru.arckontyr.rampractice.data.enums.Status

data class Character(
    val id: Int,
    val name: String,
    val status: Status,
    val species: String,
    val type: String,
    val gender: Gender,
    val origin: Location,
    val location: Location,
    val imageUrl: String,
    val episode: List<String>,
    val url: String,
    val created: String
)
