package pl.dagguh.tournaments

import org.glassfish.jersey.server.ResourceConfig
import pl.dagguh.tournaments.hipchat.HipchatCommandDispatcher
import pl.dagguh.tournaments.hipchat.TournamentHipchatResource
import pl.dagguh.tournaments.tournament.TournamentResource

class ManualResourceConfig : ResourceConfig() {

    init {
        register(HealthResource())
        val tournament = TournamentResource()
        register(tournament)
        register(TournamentHipchatResource(HipchatCommandDispatcher(tournament)))
    }
}
