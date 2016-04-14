package pl.dagguh.tournaments.hipchat

import pl.dagguh.tournaments.tournament.TournamentService
import pl.dagguh.tournaments.tournament.TournamentStartDto
import javax.ws.rs.core.Response
import javax.ws.rs.core.Response.Status.NOT_FOUND

class HipchatCommandDispatcher(private val tournament: TournamentService) {

    internal fun dispatch(command: HipchatCommand): Response {
        if (command.item.message.message.contains("start")) {
            val view = tournament.start(TournamentStartDto("Tournament via HipChat"))
            return Response.ok(view).build();
        } else {
            return Response.status(NOT_FOUND).build();
        }
    }
}
