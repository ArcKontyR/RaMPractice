package ru.arckontyr.rampractice.ui.navigation

object Routes {
    const val CHARACTERS = "characters"
    const val FAVORITES = "favorites"
    const val PROFILE = "profile"

    const val CHARACTER = "character/{id}"

    const val ID = "id"

    fun getCharacterByID(id: Int) = "character/$id"
}