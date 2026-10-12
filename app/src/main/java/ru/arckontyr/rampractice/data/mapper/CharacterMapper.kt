package ru.arckontyr.rampractice.data.mapper

import ru.arckontyr.rampractice.data.enums.Gender
import ru.arckontyr.rampractice.data.enums.Status
import ru.arckontyr.rampractice.domain.model.Character
import ru.arckontyr.rampractice.data.remote.dto.CharacterDTO

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