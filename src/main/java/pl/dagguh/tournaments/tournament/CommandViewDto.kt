package pl.dagguh.tournaments.tournament

import javax.xml.bind.annotation.XmlAttribute

data class CommandViewDto(
        var name: String = "",
        @XmlAttribute(name = "sub_commands") var subCommands: List<CommandViewDto> = listOf()
)