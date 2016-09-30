package pl.dagguh.tournaments

import pl.dagguh.tournaments.spi.ChannelAuthorizationDto
import pl.dagguh.tournaments.spi.TournamentService
import pl.dagguh.tournaments.basic.channel.ChannelDao

class TournamentServicePicker(
        private val basicTournamentService: TournamentService,
        private val hipchatTournamentService: TournamentService,
        private val channelDao: ChannelDao
) {

    fun pickDefault(): TournamentService {
        return basicTournamentService
    }

    fun pick(channelAuthorization: ChannelAuthorizationDto): TournamentService {
        val channel = channelDao.find(channelAuthorization.id)
        return channel.map {
            when (it.type) {
                "hipchat" -> hipchatTournamentService
                else -> basicTournamentService
            }
        }.orElse(basicTournamentService)
    }

}