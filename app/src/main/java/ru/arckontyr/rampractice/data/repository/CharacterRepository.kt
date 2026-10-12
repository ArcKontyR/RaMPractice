package ru.arckontyr.rampractice.data.repository

import ru.arckontyr.rampractice.data.enums.Gender
import ru.arckontyr.rampractice.data.enums.Status
import ru.arckontyr.rampractice.data.model.Character
import ru.arckontyr.rampractice.data.model.Location

object CharacterRepository {

    val characters = listOf(
        Character(
            id = 1,
            name = "Rick Sanchez",
            status = Status.ALIVE,
            species = "Human",
            type = "",
            gender = Gender.MALE,
            origin = Location("Earth (C-137)", "https://rickandmortyapi.com/api/location/1"),
            location = Location("Citadel of Ricks", "https://rickandmortyapi.com/api/location/3"),
            imageUrl = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            episode = listOf("https://rickandmortyapi.com/api/episode/1"),
            url = "https://rickandmortyapi.com/api/character/1",
            created = "2017-11-04T18:48:46.250Z"
        ),
        Character(
            id = 2,
            name = "Morty Smith",
            status = Status.ALIVE,
            species = "Human",
            type = "",
            gender = Gender.MALE,
            origin = Location("unknown", ""),
            location = Location("Citadel of Ricks", "https://rickandmortyapi.com/api/location/3"),
            imageUrl = "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
            episode = listOf("https://rickandmortyapi.com/api/episode/1"),
            url = "https://rickandmortyapi.com/api/character/2",
            created = "2017-11-04T18:50:21.651Z"
        ),
        Character(
            id = 3,
            name = "Summer Smith",
            status = Status.ALIVE,
            species = "Human",
            type = "",
            gender = Gender.FEMALE,
            origin = Location("Earth (Replacement Dimension)", "https://rickandmortyapi.com/api/location/20"),
            location = Location("Earth (Replacement Dimension)", "https://rickandmortyapi.com/api/location/20"),
            imageUrl = "https://rickandmortyapi.com/api/character/avatar/3.jpeg",
            episode = listOf("https://rickandmortyapi.com/api/episode/6"),
            url = "https://rickandmortyapi.com/api/character/3",
            created = "2017-11-04T19:09:56.428Z"
        ),
    )

    fun getCharacterById(id: Int): Character? = characters.find { it.id == id }
}