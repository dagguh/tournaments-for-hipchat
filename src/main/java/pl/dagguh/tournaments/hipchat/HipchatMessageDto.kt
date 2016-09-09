package pl.dagguh.tournaments.hipchat

data class HipchatMessageDto(
        val color: String,
        val message: String,
        val notify: Boolean,
        val messageFormat: String
)