package pl.dagguh.tournaments.channel

import com.fasterxml.jackson.annotation.JsonProperty

data class ChannelViewDto(
        @JsonProperty("id") val id: Long,
        @JsonProperty("type") val type: String
)