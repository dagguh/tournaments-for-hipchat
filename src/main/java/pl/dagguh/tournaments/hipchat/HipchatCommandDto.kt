package pl.dagguh.tournaments.hipchat

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class HipchatCommandDto(@JsonProperty("item") val item: Item) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    data class Item(
            @JsonProperty("message") val message: Message,
            @JsonProperty("room") val room: Room
    ) {
        @JsonIgnoreProperties(ignoreUnknown = true)
        data class Message(@JsonProperty("message") val message: String)

        @JsonIgnoreProperties(ignoreUnknown = true)
        data class Room(@JsonProperty("id") val id: Long)
    }
}