package pl.dagguh.tournaments.tournament

import org.apache.logging.log4j.LogManager
import java.util.*

class TournamentService(private val dao: TournamentDao) {

    fun start(start: TournamentStartDto): TournamentViewDto {
        val view = dao.create(start)
        LOG.info("{} started as {}", start, view)
        return view
    }

    fun show(id: Long): Optional<TournamentViewDto> {
        return dao.findById(id)
    }

    companion object {
        private val LOG = LogManager.getLogger(TournamentService::class)
    }
}