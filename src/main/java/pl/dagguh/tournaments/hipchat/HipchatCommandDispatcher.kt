package pl.dagguh.tournaments.hipchat

import pl.dagguh.tournaments.tournament.TournamentService
import pl.dagguh.tournaments.tournament.TournamentStartDto
import javax.ws.rs.core.Response
import javax.ws.rs.core.Response.Status.NOT_FOUND

class HipchatCommandDispatcher(private val tournament: TournamentService) {

    internal fun dispatch(command: HipchatCommandDto): Response {
        if (command.item.message.message.startsWith("/tournament start")) {
            return start()
        } else {
            return Response.status(NOT_FOUND).build();
        }
    }

    private fun start(): Response {
        println("tournament start")
        val view = tournament.start(TournamentStartDto("Tournament via HipChat"))
        return Response.ok(HipchatMessageDto(
                "green",
                "Tournament T%s started.".format(view.id),
                false,
                "text"
        )).build();
    }
}
