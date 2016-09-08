package pl.dagguh.tournaments.tournament

import java.util.*

class TournamentDao {

    private val views: MutableMap<Long, TournamentViewDto> = HashMap()
    /**
     * Needs to be guarded for thread safety
     */
    private var nextId: Long = 1L

    fun create(start: TournamentStartDto): TournamentViewDto {
        synchronized(views, {
            val view = TournamentViewDto(id = nextId, title = start.title)
            views.put(nextId, view)
            nextId++
            return view
        })
    }

    fun findById(id: Long): Optional<TournamentViewDto> {
        return Optional.ofNullable(views[id])
    }
}