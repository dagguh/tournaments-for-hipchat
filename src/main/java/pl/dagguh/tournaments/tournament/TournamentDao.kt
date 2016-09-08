package pl.dagguh.tournaments.tournament

import java.util.*
import javax.persistence.EntityManager

class TournamentDao(private val entityManager: EntityManager) {

    fun create(start: TournamentStartDto): TournamentViewDto {
        val tournament = TournamentEntity(title = start.title)
        entityManager.persist(tournament)
        return TournamentViewDto(tournament.id!!, tournament.title)
    }

    fun findById(id: Long): Optional<TournamentViewDto> {
        val tournament = entityManager.find(TournamentEntity::class.java, id)
        if (tournament == null) {
            return Optional.empty()
        } else {
            return Optional.of(TournamentViewDto(tournament.id!!, tournament.title))
        }
    }
}