package pl.dagguh.tournaments

import org.glassfish.jersey.server.ResourceConfig
import pl.dagguh.tournaments.hipchat.HipchatCommandDispatcher
import pl.dagguh.tournaments.hipchat.TournamentHipchatResource
import pl.dagguh.tournaments.tournament.TournamentDao
import pl.dagguh.tournaments.tournament.TournamentResource

class ManualResourceConfig : ResourceConfig() {

    init {
        register(ServerErrorLogger())
        register(HealthResource())
        val tournament = TournamentResource(TournamentDao())
        register(tournament)
        register(TournamentHipchatResource(HipchatCommandDispatcher(tournament)))
    }
}
