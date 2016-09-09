package pl.dagguh.tournaments.channel

import com.fasterxml.jackson.annotation.JsonProperty

data class ChannelAuthorizationDto(
        @JsonProperty("id") val id: Long
)