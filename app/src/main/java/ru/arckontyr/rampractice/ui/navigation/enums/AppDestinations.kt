package ru.arckontyr.rampractice.ui.navigation.enums

import ru.arckontyr.rampractice.R
import ru.arckontyr.rampractice.ui.navigation.Routes

enum class AppDestinations(
    val label: String,
    val icon: Int,
    val route: String
) {
    CHARACTERS("Characters", R.drawable.ic_home, Routes.CHARACTERS),
    FAVORITES("Favorites", R.drawable.ic_favorite, Routes.FAVORITES),
    PROFILE("Profile", R.drawable.ic_account_box, Routes.PROFILE)
}