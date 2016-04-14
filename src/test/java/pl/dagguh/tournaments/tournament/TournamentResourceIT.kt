package pl.dagguh.tournaments.tournament

import org.glassfish.jersey.client.ClientConfig
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.not
import org.junit.Assert.assertThat
import org.junit.Test
import javax.json.Json.createObjectBuilder
import javax.json.JsonObject
import javax.json.JsonObjectBuilder
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.client.Entity.json
import javax.ws.rs.client.WebTarget

class TournamentResourceIT {

    val tournament: WebTarget

    init {
        val clientConfig = ClientConfig()
        val client = ClientBuilder.newClient(clientConfig)
        tournament = client.target("http://localhost:8080/tournament")
    }

    @Test
    fun shouldStartWithGivenTitle() {
        val start = buildStart()
                .add("title", "alpha")
                .build()

        val view = tournament
                .path("start")
                .request()
                .post(json(start))
                .readEntity(JsonObject::class.java)

        assertThat(view.getString("title"), equalTo("alpha"))
    }

    private fun buildStart(): JsonObjectBuilder {
        return createObjectBuilder()
                .add("title", "dummyTitle")
    }

    private fun start(start: JsonObject): JsonObject {
        return tournament
                .path("start")
                .request()
                .post(json(start))
                .readEntity(JsonObject::class.java)
    }

    @Test
    fun shouldStartWithDifferentIdsDespiteSameRequest() {
        val firstView = start(buildStart().build())
        val secondView = start(buildStart().build())

        assertThat(firstView.getInt("id"), not(equalTo(secondView.getInt("id"))))
    }

    @Test
    fun shouldShowStartedTournament() {
        val startedView = start(buildStart().build())
        val shownView = tournament
                .path("show")
                .queryParam("id", startedView.getInt("id"))
                .request()
                .get()
                .readEntity(JsonObject::class.java)

        assertThat(startedView, equalTo(shownView))
    }
}