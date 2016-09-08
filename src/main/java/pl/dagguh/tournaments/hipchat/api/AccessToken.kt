package pl.dagguh.tournaments.hipchat.api

import java.time.LocalDateTime

data class AccessToken (
    val expires: LocalDateTime,
    val token: String
)