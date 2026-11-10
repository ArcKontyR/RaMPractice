package ru.arckontyr.rampractice.ui.navigation

object Routes {
    const val CHARACTERS = "characters"
    const val FAVORITES = "favorites"
    const val PROFILE = "profile"
    const val EDIT_PROFILE = "edit_profile"
    const val FILTER = "filter"
    const val CHARACTER = "character/{id}"
    const val ID = "id"

    fun getCharacterByID(id: Int) = "character/$id"
}