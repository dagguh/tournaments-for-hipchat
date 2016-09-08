package pl.dagguh.tournaments.tournament

import javax.xml.bind.annotation.XmlAttribute

data class CommandViewDto(
        val name: String,
        @XmlAttribute(name = "sub_commands") val subCommands: List<CommandViewDto> = listOf()
)