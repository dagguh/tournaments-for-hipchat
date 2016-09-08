package pl.dagguh.tournaments.hipchat

data class HipchatResponseDto(
        val color: String,
        val message: String,
        val notify: Boolean,
        val messageFormat: String
)