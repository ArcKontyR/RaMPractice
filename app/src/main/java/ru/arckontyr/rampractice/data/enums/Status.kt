package ru.arckontyr.rampractice.data.enums

enum class Status(val state: String) {
    DEAD("Dead"),
    ALIVE("Alive"),
    UNKNOWN("unknown");

    companion object {
        operator fun invoke(token: String): Status {
            val s: Status? = entries.find { it.name == token.trim().uppercase() }
            return s ?: UNKNOWN
        }
    }
}