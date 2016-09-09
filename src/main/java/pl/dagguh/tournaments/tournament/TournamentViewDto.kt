package pl.dagguh.tournaments.tournament

import pl.dagguh.tournaments.channel.ChannelViewDto

data class TournamentViewDto(
        val id: Long,
        val title: String,
        val channel: ChannelViewDto
)