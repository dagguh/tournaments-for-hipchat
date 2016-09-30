package pl.dagguh.tournaments.hipchat

import pl.dagguh.tournaments.channel.ChannelAuthorizationDto
import pl.dagguh.tournaments.channel.ChannelViewDto
import pl.dagguh.tournaments.tournament.TournamentCreationDto
import pl.dagguh.tournaments.tournament.TournamentService

class HipchatCommandDispatcher(private val tournamentService: TournamentService) {

    internal fun dispatch(command: HipchatCommandDto) {
        if (command.item.message.message.startsWith("/tournament create")) {
            create()
        }
    }

    private fun create() {
        val channel = ChannelViewDto(2345L, "hipchat")
        tournamentService.create(TournamentCreationDto(
                title = "Tournament from HipChat",
                channel = ChannelAuthorizationDto(id = channel.id)
        ))
    }
}
