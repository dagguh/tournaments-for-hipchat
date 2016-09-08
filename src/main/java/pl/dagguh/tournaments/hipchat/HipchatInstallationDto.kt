package pl.dagguh.tournaments.hipchat

data class HipchatInstallationDto(
        var oauthId: String = "",
        var capabilitiesUrl: String = "",
        var roomId: Int = 0,
        var groupId: Int = 0,
        var oauthSecret: String = "",
        var tokenUrl: String = "",
        var apiUrl: String = ""
)