package pl.dagguh.tournaments.spi

import java.util.*

interface TournamentService {

    fun create(creation: TournamentCreationDto): TournamentViewDto
    fun start(start: TournamentStartDto)
    fun show(id: Long): Optional<TournamentViewDto>
}