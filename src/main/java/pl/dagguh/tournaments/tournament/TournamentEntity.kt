package pl.dagguh.tournaments.tournament

import java.io.Serializable
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class TournamentEntity(
        @Id
        @GeneratedValue
        var id: Long? = null,
        var title: String = ""
) : Serializable