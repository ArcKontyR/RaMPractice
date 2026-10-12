package ru.arckontyr.rampractice.data.enums

enum class Gender(val g: String) {
    MALE("Male"),
    FEMALE("Female"),
    GENDERLESS("Genderless"),
    UNKNOWN("unknown");

    companion object {
        operator fun invoke(token: String): Gender {
            val s: Gender? = entries.find { it.name == token.trim().uppercase() }
            return s ?: UNKNOWN
        }
    }
}