package pl.dagguh.tournaments.hipchat

import com.fasterxml.jackson.annotation.JsonProperty

data class HipchatCommandDto(@JsonProperty("item") val item: Item) {

    data class Item(
            @JsonProperty("message") val message: Message,
            @JsonProperty("room") val room: Room
    ) {

        data class Message(@JsonProperty("message") val message: String)

        data class Room(@JsonProperty("id") val id: Long)
    }
}