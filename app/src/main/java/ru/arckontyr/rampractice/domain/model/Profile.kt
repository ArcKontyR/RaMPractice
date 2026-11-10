package ru.arckontyr.rampractice.domain.model

data class Profile(
    val name: String = "",
    val job: String = "",
    val resumeUrl: String = "",
    val avatarUri: String = ""
) {
    companion object {
        val Default = Profile()
    }
}