package pl.dagguh.tournaments

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider
import org.glassfish.jersey.server.ResourceConfig
import pl.dagguh.tournaments.channel.ChannelDao
import pl.dagguh.tournaments.channel.ChannelResource
import pl.dagguh.tournaments.hipchat.*
import pl.dagguh.tournaments.hipchat.api.HipchatApiService
import pl.dagguh.tournaments.tournament.DaoTournamentService
import pl.dagguh.tournaments.tournament.TournamentDao
import pl.dagguh.tournaments.tournament.TournamentResource
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.Persistence

class ManualResourceConfig : ResourceConfig() {

    init {
        configureJackson()
        register(ServerErrorLogger())
        register(HealthResource())
        val entityManager = createEntityManager()
        val installations = InstallationDao()
        val urls = HipchatServerUrlsDao()
        val api = HipchatApiService(installations, urls)
        val channelDao = ChannelDao(entityManager)
        val basicTournamentService = DaoTournamentService(TournamentDao(entityManager))
        val tournament = SwitchingTournamentService(
                TournamentServicePicker(
                        basicTournamentService,
                        HipchatTournamentService(basicTournamentService, installations),
                        channelDao
                )
        )
        register(ChannelResource(channelDao))
        register(TournamentResource(tournament))
        register(TournamentHipchatResource(HipchatCommandDispatcher(tournament), api, installations, urls))
    }

    private fun configureJackson() {
        val provider = JacksonJaxbJsonProvider()
        provider.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        register(provider)
    }

    private fun createEntityManager(): EntityManager {
        val overrides = LinkedHashMap<String, Any>()
        val jdbcUrl = System.getenv("JDBC_DATABASE_URL")
        if (jdbcUrl != null) {
            overrides["javax.persistence.jdbc.url"] = jdbcUrl
        }
        return Persistence.createEntityManagerFactory("tournament", overrides).createEntityManager()
    }
}
