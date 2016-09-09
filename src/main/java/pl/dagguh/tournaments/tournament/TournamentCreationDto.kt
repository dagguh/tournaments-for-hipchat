package pl.dagguh.tournaments.tournament

import com.fasterxml.jackson.annotation.JsonProperty
import pl.dagguh.tournaments.channel.ChannelAuthorizationDto

data class TournamentCreationDto(
        @JsonProperty("title") val title: String,
        @JsonProperty("channel") val channel: ChannelAuthorizationDto
)
