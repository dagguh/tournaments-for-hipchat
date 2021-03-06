package pl.dagguh.tournaments.basic.tournament

import pl.dagguh.tournaments.spi.TournamentCreationDto
import pl.dagguh.tournaments.spi.TournamentViewDto
import pl.dagguh.tournaments.basic.channel.ChannelEntity
import java.util.*
import javax.persistence.EntityManager

class TournamentDao(private val entityManager: EntityManager) {

    fun create(creation: TournamentCreationDto): TournamentViewDto {
        val tournament = TournamentEntity(
                title = creation.title,
                channel = ChannelEntity(id = creation.channel.id)
        )
        entityManager.persist(tournament)
        return tournament.toViewDto()
    }

    fun find(id: Long): Optional<TournamentViewDto> {
        val tournament = entityManager.find(TournamentEntity::class.java, id)
        if (tournament == null) {
            return Optional.empty()
        } else {
            return Optional.of(tournament.toViewDto())
        }
    }
}