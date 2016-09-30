package pl.dagguh.tournaments.spi

import com.fasterxml.jackson.annotation.JsonProperty

data class ChannelAuthorizationDto(
        @JsonProperty("id") val id: Long
)