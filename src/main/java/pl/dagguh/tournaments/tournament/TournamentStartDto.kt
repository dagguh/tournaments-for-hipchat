package pl.dagguh.tournaments.tournament

import com.fasterxml.jackson.annotation.JsonProperty

data class TournamentStartDto(@JsonProperty("title") val title: String)
