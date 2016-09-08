package pl.dagguh.tournaments.tournament

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.not
import org.junit.Assert.assertThat
import org.junit.Test
import pl.dagguh.tournaments.IntegrationEnvironment
import javax.json.JsonArray
import javax.json.JsonObject
import javax.json.JsonObjectBuilder
import javax.ws.rs.client.Entity.json
import javax.ws.rs.client.WebTarget
import javax.json.Json.createArrayBuilder as startArray
import javax.json.Json.createObjectBuilder as startObject

class TournamentResourceIT {

    val tournament: WebTarget = IntegrationEnvironment().getRoot().path("tournament")

    @Test
    fun shouldListInitialCommands() {
        val actualCommands = tournament
                .path("commands")
                .request()
                .get()
                .readEntity(JsonArray::class.java)

        val expectedCommands = startArray()
                .add(buildFlatCommand("create"))
                .add(startObject()
                        .add("name", "set")
                        .add(
                                "sub_commands",
                                startArray()
                                        .add(buildFlatCommand("players"))
                                        .add(buildFlatCommand("title"))
                                        .build()
                        )
                        .build()
                )
                .add(buildFlatCommand("show"))
                .add(buildFlatCommand("start"))
                .add(startObject()
                        .add("name", "use")
                        .add(
                                "sub_commands",
                                startArray()
                                        .add(buildFlatCommand("swiss"))
                                        .build()
                        )
                        .build()
                )
                .build()
        assertThat(actualCommands, equalTo(expectedCommands))
    }

    private fun buildFlatCommand(commandName: String): JsonObject? {
        return startObject()
                .add("name", commandName)
                .add("sub_commands", startArray().build())
                .build()
    }

    @Test
    fun shouldStartWithGivenTitle() {
        val start = startStartup()
                .add("title", "alpha")
                .build()

        val view = tournament
                .path("start")
                .request()
                .post(json(start))
                .readEntity(JsonObject::class.java)

        assertThat(view.getString("title"), equalTo("alpha"))
    }

    private fun startStartup(): JsonObjectBuilder {
        return startObject()
                .add("title", "dummyTitle")
    }

    private fun startUp(start: JsonObject): JsonObject {
        return tournament
                .path("start")
                .request()
                .post(json(start))
                .readEntity(JsonObject::class.java)
    }

    @Test
    fun shouldStartWithDifferentIdsDespiteSameRequest() {
        val firstView = startUp(startStartup().build())
        val secondView = startUp(startStartup().build())

        assertThat(firstView.getInt("id"), not(equalTo(secondView.getInt("id"))))
    }

    @Test
    fun shouldShowStartedTournament() {
        val startedView = startUp(startStartup().build())
        val shownView = tournament
                .path("show")
                .queryParam("id", startedView.getInt("id"))
                .request()
                .get()
                .readEntity(JsonObject::class.java)

        assertThat(startedView, equalTo(shownView))
    }
}