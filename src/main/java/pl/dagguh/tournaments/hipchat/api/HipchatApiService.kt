package pl.dagguh.tournaments.hipchat.api

import org.glassfish.jersey.client.ClientConfig
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature
import pl.dagguh.tournaments.hipchat.HipchatInstallationDto
import pl.dagguh.tournaments.hipchat.HipchatServerUrlsDao
import pl.dagguh.tournaments.hipchat.InstallationDao
import java.time.LocalDateTime
import java.util.*
import javax.json.JsonObject
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.client.Entity
import javax.ws.rs.core.Form

class HipchatApiService(val installationDao: InstallationDao, val hipchatServerUrlsDao: HipchatServerUrlsDao) {
    private val tokens: MutableMap<String, AccessToken> = HashMap();

    fun postToRoom(oauthId: String, message: String) {
//        var token: AccessToken = tokens.getOrPut(oauthId, () -> fetchToken(oauthId))
        if (!tokens.containsKey(oauthId)) {
            tokens.put(oauthId, fetchToken(oauthId));
        }

    }

    private fun fetchToken(oauthId: String): AccessToken {
        val installation: HipchatInstallationDto = installationDao.get(oauthId).get();
        val httpAuth: HttpAuthenticationFeature = HttpAuthenticationFeature.basic(installation.oauthId, installation.oauthSecret);

        val tokenResponse:JsonObject = ClientBuilder.newClient(ClientConfig())
            .target(hipchatServerUrlsDao.get(oauthId).get().tokenUrl)
            .register(httpAuth)
            .request()
            .post(Entity.form(Form("grant_type", "client_credentials")))
            .readEntity(JsonObject::class.java)

        println(tokenResponse);
        return AccessToken(LocalDateTime.now().plusSeconds(
                tokenResponse.getInt("expires_in").toLong()),
                tokenResponse.getString("access_token")
        );
    }
}