package ru.arckontyr.rampractice.data.mapper

import ru.arckontyr.rampractice.domain.model.Location
import ru.arckontyr.rampractice.data.remote.dto.LocationDTO

fun LocationDTO.toDomain(): Location = Location(
    name = name,
    url = url
)