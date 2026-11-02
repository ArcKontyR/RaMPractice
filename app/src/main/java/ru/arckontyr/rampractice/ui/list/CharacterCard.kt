package ru.arckontyr.rampractice.ui.list

import ru.arckontyr.rampractice.domain.model.Location

data class CharacterCard(
    val id: Int,
    val name: String,
    val subtitle: String,
    val location: Location,
    val imageUrl: String
)