package pl.dagguh.tournaments.hipchat

import java.util.*

class HipchatServerUrlsDao {

    private val urlStore: MutableMap<String, HipchatServerUrlsDto> = HashMap()

    fun store(oauthId: String, urls: HipchatServerUrlsDto) {
        urlStore.put(oauthId, urls)
    }

    fun get(oauthId: String): Optional<HipchatServerUrlsDto> {
        return Optional.ofNullable(urlStore[oauthId])
    }
}