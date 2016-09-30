package pl.dagguh.tournaments.spi

import com.fasterxml.jackson.annotation.JsonProperty

data class TournamentCreationDto(
        @JsonProperty("title") val title: String,
        @JsonProperty("channel") val channel: ChannelAuthorizationDto
)
