package pl.dagguh.tournaments.tournament

import org.glassfish.jersey.client.ClientConfig
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.client.Entity.entity
import javax.ws.rs.client.WebTarget
import javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE

class TournamentResourceIT {

    val tournament: WebTarget

    init {
        val clientConfig = ClientConfig()
        val client = ClientBuilder.newClient(clientConfig)
        tournament = client.target("http://localhost:8080/tournament")
    }

    @Test
    fun shouldStartWithGivenTitle() {
        val view = start(TournamentStartDto("alpha"))

        assertThat(view.title, equalTo("alpha"))
    }

    private fun start(start: TournamentStartDto): TournamentViewDto {
        return tournament
                .path("start")
                .request()
                .post(entity(start, APPLICATION_JSON_TYPE))
                .readEntity(TournamentViewDto::class.java)
    }

    @Test
    fun shouldStartWithDifferentIdsDespiteSameTitles() {
        val firstView = start(TournamentStartDto("beta"))
        val secondView = start(TournamentStartDto("beta"))

        assertThat(firstView.id, CoreMatchers.not(equalTo(secondView.id)))
    }

    @Test
    fun shouldShowStartedTournament() {
        val startedView = start(TournamentStartDto("gamma"))
        val shownView = tournament
                .path("show")
                .queryParam("id", startedView.id)
                .request()
                .get()
                .readEntity(TournamentViewDto::class.java)

        assertThat(startedView, equalTo(shownView))
    }

}