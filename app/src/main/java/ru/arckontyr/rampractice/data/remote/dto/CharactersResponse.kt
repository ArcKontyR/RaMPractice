package ru.arckontyr.rampractice.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CharactersResponse(
    val info: InfoDTO,
    val results: List<CharacterDTO>
)