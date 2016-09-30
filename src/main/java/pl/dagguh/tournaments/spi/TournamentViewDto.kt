package pl.dagguh.tournaments.spi

data class TournamentViewDto(
        val id: Long,
        val title: String,
        val channel: ChannelViewDto
)