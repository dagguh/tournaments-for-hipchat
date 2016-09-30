package pl.dagguh.tournaments

import pl.dagguh.tournaments.spi.TournamentCreationDto
import pl.dagguh.tournaments.spi.TournamentService
import pl.dagguh.tournaments.spi.TournamentStartDto
import pl.dagguh.tournaments.spi.TournamentViewDto
import java.util.*

class SwitchingTournamentService(
        private val picker: TournamentServicePicker
) : TournamentService {

    override fun create(creation: TournamentCreationDto): TournamentViewDto {
        return picker.pick(creation.channel).create(creation)
    }

    override fun start(start: TournamentStartDto) {
        return picker.pick(start.channel).start(start)
    }

    override fun show(id: Long): Optional<TournamentViewDto> {
        return picker.pickDefault().show(id)
    }
}
