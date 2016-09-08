package pl.dagguh.tournaments.hipchat

import java.util.*

class InstallationDao {

    private val installations: MutableMap<String, HipchatInstallationDto> = HashMap()
    fun store(ouathId: String, installationData: HipchatInstallationDto) {
        installations.put(ouathId, installationData);
    }

    fun get(oauthId: String): Optional<HipchatInstallationDto> {
        return Optional.ofNullable(installations[oauthId])
    }
}