package pl.dagguh.tournaments.hipchat

data class HipchatInstallationDto(
        var ouathId: String = "",
        var capabilitiesUrl: String = "",
        var roomId: Int = 0,
        var groupId: Int = 0,
        var ouathSecret: String = ""
)