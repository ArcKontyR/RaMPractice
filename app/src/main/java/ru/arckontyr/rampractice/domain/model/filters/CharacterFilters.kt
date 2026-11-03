package ru.arckontyr.rampractice.domain.model.filters

import ru.arckontyr.rampractice.data.enums.Gender
import ru.arckontyr.rampractice.data.enums.Status

data class CharacterFilter(
    val name: String = "",
    val status: Status? = null,
    val species: String = "",
    val type: String = "",
    val gender: Gender? = null
) {
    val isDefault: Boolean
        get() = name.isBlank() && status == null &&
                species.isBlank() && type.isBlank() && gender == null

    companion object {
        val Default = CharacterFilter()
    }
}