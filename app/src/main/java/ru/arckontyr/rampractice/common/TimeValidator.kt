package ru.arckontyr.rampractice.common

private val TIME_REGEX = Regex("""^([01]\d|2[0-3]):([0-5]\d)$""")

fun String.isValidTime(): Boolean = TIME_REGEX.matches(this)