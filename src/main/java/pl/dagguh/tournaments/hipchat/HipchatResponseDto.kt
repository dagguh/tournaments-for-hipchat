package pl.dagguh.tournaments.hipchat

import javax.xml.bind.annotation.XmlAttribute

data class HipchatResponseDto(
        var color: String = "",
        var message: String = "",
        var notify: Boolean = false,
        @XmlAttribute(name = "message_format") var messageFormat: String = ""
)