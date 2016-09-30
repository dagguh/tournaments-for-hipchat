package pl.dagguh.tournaments.hipchat

import pl.dagguh.tournaments.spi.ChannelAuthorizationDto
import pl.dagguh.tournaments.spi.ChannelViewDto
import pl.dagguh.tournaments.spi.TournamentCreationDto
import pl.dagguh.tournaments.spi.TournamentService

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
