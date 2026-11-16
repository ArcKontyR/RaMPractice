package ru.arckontyr.rampractice.data.mapper

import ru.arckontyr.rampractice.data.enums.Gender
import ru.arckontyr.rampractice.data.enums.Status
import ru.arckontyr.rampractice.data.local.entities.FavoriteCharacter
import ru.arckontyr.rampractice.domain.model.Character
import ru.arckontyr.rampractice.data.remote.dto.CharacterDTO
import ru.arckontyr.rampractice.domain.model.Location

fun CharacterDTO.toDomain(): Character = Character(
    id = id,
    name = name,
    status = Status(status),
    species = species,
    type = type,
    gender = Gender(gender),
    origin = origin.toDomain(),
    location = location.toDomain(),
    imageUrl = imageUrl,
    episode = episode,
    url = url,
    created = created
)

fun Character.toEntity(): FavoriteCharacter = FavoriteCharacter(
    id = id,
    name = name,
    status = status.state,
    species = species,
    type = type,
    gender = gender.state,
    originName = origin.name,
    originUrl = origin.url,
    locationName = location.name,
    locationUrl = location.url,
    imageUrl = imageUrl,
    episode = episode.joinToString("|"),
    url = url,
    created = created
)

fun FavoriteCharacter.toDomain(): Character = Character(
    id = id,
    name = name,
    status = Status(status),
    species = species,
    type = type,
    gender = Gender(gender),
    origin = Location(originName, originUrl),
    location = Location(locationName, locationUrl),
    imageUrl = imageUrl,
    episode = if (episode.isBlank()) emptyList() else episode.split("|"),
    url = url,
    created = created
)