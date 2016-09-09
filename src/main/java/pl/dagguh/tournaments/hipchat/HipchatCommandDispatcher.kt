package pl.dagguh.tournaments.hipchat

import pl.dagguh.tournaments.ServiceRouter
import pl.dagguh.tournaments.channel.ChannelAuthorizationDto
import pl.dagguh.tournaments.channel.ChannelViewDto
import pl.dagguh.tournaments.tournament.TournamentCreationDto

class HipchatCommandDispatcher(private val router: ServiceRouter) {

    internal fun dispatch(command: HipchatCommandDto) {
        if (command.item.message.message.startsWith("/tournament create")) {
            create()
        }
    }

    private fun create() {
        val channel = ChannelViewDto(2345L, "hipchat")
        router
                .tournament(channel)
                .create(TournamentCreationDto(
                        title = "Tournament from HipChat",
                        channel = ChannelAuthorizationDto(id = channel.id)
                ))
    }
}
