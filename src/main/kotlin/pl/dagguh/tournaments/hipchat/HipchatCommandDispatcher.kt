package pl.dagguh.tournaments.hipchat

import pl.dagguh.tournaments.tournament.TournamentResource
import pl.dagguh.tournaments.tournament.TournamentStartDto
import javax.ws.rs.core.Response

class HipchatCommandDispatcher(private val tournament: TournamentResource) {

    internal fun dispatch(command: HipchatCommand): Response {
        return tournament.start(TournamentStartDto("Tournament via HipChat"))
    }

}
