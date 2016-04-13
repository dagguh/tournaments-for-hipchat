package pl.dagguh.tournaments.tournament

import org.apache.logging.log4j.LogManager
import java.util.*
import java.util.Optional.ofNullable

class TournamentDao {

    private val views: MutableMap<Long, TournamentViewDto> = HashMap()
    /**
     * Needs to be guarded for thread safety
     */
    private var nextId: Long = 1L

    fun start(start: TournamentStartDto): TournamentViewDto {
        synchronized(views, {
            val view = TournamentViewDto()
            view.id = nextId
            view.title = start.title
            views.put(nextId, view)
            LOG.info("{} started as {}", start, view)
            nextId++
            return view
        })
    }

    fun show(id: Long): Optional<TournamentViewDto> {
        return ofNullable(views[id])
    }


    companion object {
        private val LOG = LogManager.getLogger(TournamentDao::class)
    }
}