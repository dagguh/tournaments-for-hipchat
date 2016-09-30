package pl.dagguh.tournaments.spi

import com.fasterxml.jackson.annotation.JsonProperty

data class TournamentStartDto(
        @JsonProperty("id") val id: Long,
        @JsonProperty("channel") val channel: ChannelAuthorizationDto
)
