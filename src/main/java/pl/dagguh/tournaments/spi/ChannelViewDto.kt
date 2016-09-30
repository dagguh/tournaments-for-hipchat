package pl.dagguh.tournaments.spi

import com.fasterxml.jackson.annotation.JsonProperty

data class ChannelViewDto(
        @JsonProperty("id") val id: Long,
        @JsonProperty("type") val type: String
)