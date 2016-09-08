package pl.dagguh.tournaments.hipchat

import org.apache.logging.log4j.LogManager
import org.glassfish.jersey.client.ClientConfig
import pl.dagguh.tournaments.hipchat.api.HipchatApiService
import javax.json.JsonObject
import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("hipchat")
class TournamentHipchatResource(private val dispatcher: HipchatCommandDispatcher,
                                private val api: HipchatApiService,
                                private val installations: InstallationDao,
                                private val urls: HipchatServerUrlsDao) {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun commandViaHipChat(command: HipchatCommandDto): Response {
        LOG.info("Dispatching {}", command)
        return dispatcher.dispatch(command)
    }

    @POST
    @Path("installed")
    @Consumes(MediaType.APPLICATION_JSON)
    fun addonInstalled(installationData: HipchatInstallationDto): Response {
        val oauthId = installationData.oauthId;

        installations.store(oauthId, installationData)

        val clientConfig = ClientConfig()
        val capabilities = ClientBuilder.newClient(clientConfig)
                .target(installationData.capabilitiesUrl)
                .request()
                .get()
                .readEntity(JsonObject::class.java)

        val tokenUrl = capabilities
                .getJsonObject("capabilities")
                .getJsonObject("oauth2Provider")
                .getString("tokenUrl")

        val apiUrl = capabilities
                .getJsonObject("capabilities")
                .getJsonObject("hipchatApiProvider")
                .getString("url")

        urls.store(oauthId, HipchatServerUrlsDto(tokenUrl, apiUrl))

        api.postToRoom(oauthId, "Hello");
        return Response.ok().build()
    }

    companion object {
        private val LOG = LogManager.getLogger(TournamentHipchatResource::class)
    }
}

