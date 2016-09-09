package pl.dagguh.tournaments

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider
import org.glassfish.jersey.server.ResourceConfig
import pl.dagguh.tournaments.hipchat.HipchatCommandDispatcher
import pl.dagguh.tournaments.hipchat.HipchatServerUrlsDao
import pl.dagguh.tournaments.hipchat.InstallationDao
import pl.dagguh.tournaments.hipchat.TournamentHipchatResource
import pl.dagguh.tournaments.hipchat.api.HipchatApiService
import pl.dagguh.tournaments.tournament.TournamentDao
import pl.dagguh.tournaments.tournament.TournamentResource
import pl.dagguh.tournaments.tournament.TournamentService
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.Persistence

class ManualResourceConfig : ResourceConfig() {

    init {
        configureJackson()
        val entityManager = createEntityManager()
        register(ServerErrorLogger())
        register(HealthResource())
        val tournament = TournamentService(TournamentDao(entityManager))
        val installations = InstallationDao()
        val urls = HipchatServerUrlsDao()
        val api = HipchatApiService(installations, urls)
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
