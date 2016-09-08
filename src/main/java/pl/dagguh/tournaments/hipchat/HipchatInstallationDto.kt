package pl.dagguh.tournaments.hipchat

import com.fasterxml.jackson.annotation.JsonProperty

data class HipchatInstallationDto(
        @JsonProperty("oauthId") val oauthId: String,
        @JsonProperty("capabilitiesUrl") val capabilitiesUrl: String,
        @JsonProperty("roomId") val roomId: Int,
        @JsonProperty("groupId") val groupId: Int,
        @JsonProperty("oauthSecret") val oauthSecret: String,
        @JsonProperty("tokenUrl") val tokenUrl: String,
        @JsonProperty("apiUrl") val apiUrl: String
)