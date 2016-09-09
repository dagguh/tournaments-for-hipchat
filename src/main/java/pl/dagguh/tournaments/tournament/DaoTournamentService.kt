package pl.dagguh.tournaments.tournament

import org.apache.logging.log4j.LogManager
import java.util.*

class DaoTournamentService(private val dao: TournamentDao) : TournamentService {

    override fun create(creation: TournamentCreationDto): TournamentViewDto {
        val tournament = dao.create(creation)
        LOG.info("{} created for {}", tournament, creation)
        return tournament
    }

    override fun start(start: TournamentStartDto) {
        LOG.info("{} started", start)
    }

    override fun show(id: Long): Optional<TournamentViewDto> {
        return dao.find(id)
    }

    companion object {
        private val LOG = LogManager.getLogger(TournamentService::class)
    }
}