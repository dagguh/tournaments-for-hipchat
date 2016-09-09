package pl.dagguh.tournaments

import pl.dagguh.tournaments.channel.ChannelViewDto
import pl.dagguh.tournaments.tournament.TournamentService
import java.util.Optional.ofNullable

class ServiceRouter(
        private val tournamentSuppliers: Map<String, TournamentService>,
        private val defaultTournamentService: TournamentService
) {

    fun tournament(channel: ChannelViewDto): TournamentService {
        return ofNullable(tournamentSuppliers[channel.type])
                .orElse(defaultTournamentService)!!
    }
}