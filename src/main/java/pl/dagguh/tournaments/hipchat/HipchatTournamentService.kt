package pl.dagguh.tournaments.hipchat

import org.apache.logging.log4j.LogManager
import pl.dagguh.tournaments.spi.TournamentCreationDto
import pl.dagguh.tournaments.spi.TournamentService
import pl.dagguh.tournaments.spi.TournamentStartDto
import pl.dagguh.tournaments.spi.TournamentViewDto
import java.util.*

class HipchatTournamentService(
        private val tournamentService: TournamentService,
        private val installationDao: InstallationDao
) : TournamentService {

    override fun create(creation: TournamentCreationDto): TournamentViewDto {
        LOG.info { "Creating %s via one of HipChat channels from %s".format(creation, installationDao) }
        return tournamentService.create(creation)
    }

    override fun show(id: Long): Optional<TournamentViewDto> {
        return tournamentService.show(id)
    }

    override fun start(start: TournamentStartDto) {
        return tournamentService.start(start)
    }

    companion object {
        private val LOG = LogManager.getLogger(HipchatTournamentService::class)
    }

}