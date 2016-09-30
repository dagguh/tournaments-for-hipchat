package pl.dagguh.tournaments.basic.tournament

import pl.dagguh.tournaments.spi.TournamentViewDto
import pl.dagguh.tournaments.basic.channel.ChannelEntity
import java.io.Serializable
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
data class TournamentEntity(
        @Id
        @GeneratedValue
        var id: Long? = null,
        var title: String = "",
        @ManyToOne
        var channel: ChannelEntity? = null
) : Serializable {

    fun toViewDto(): TournamentViewDto {
        return TournamentViewDto(
                id = id!!,
                title = title,
                channel = channel!!.toViewDto()
        )
    }
}