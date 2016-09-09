package pl.dagguh.tournaments.tournament

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.not
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import pl.dagguh.tournaments.IntegrationEnvironment
import pl.dagguh.tournaments.channel.ChannelViewDto
import javax.json.JsonArray
import javax.json.JsonObject
import javax.json.JsonObjectBuilder
import javax.ws.rs.client.Entity.json
import javax.ws.rs.client.Entity.text
import javax.ws.rs.client.WebTarget
import javax.json.Json.createArrayBuilder as startArray
import javax.json.Json.createObjectBuilder as startObject

class TournamentResourceIT {

    val tournaments: WebTarget = IntegrationEnvironment().getRoot().path("tournament")
    val channels: WebTarget = IntegrationEnvironment().getRoot().path("channel")
    var channelId: Long? = null

    @Before
    fun setUp() {
        channelId = channels
                .request()
                .post(text(""), ChannelViewDto::class.java)
                .id

    }

    @Test
    fun shouldListInitialCommands() {
        val actualCommands = tournaments
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
        val creation = startCreation()
                .add("title", "alpha")
                .build()

        val view = create(creation)

        assertThat(view.getString("title"), equalTo("alpha"))
    }

    private fun startCreation(): JsonObjectBuilder {
        return startObject()
                .add("title", "dummy title")
                .add("channel", startObject().add("id", channelId!!).build())
    }

    private fun create(creation: JsonObject): JsonObject {
        val response = tournaments
                .path("create")
                .request()
                .post(json(creation))
        return response
                .readEntity(JsonObject::class.java)
    }

    @Test
    fun shouldCreateWithDifferentIds() {
        val firstView = create(startCreation().build())
        val secondView = create(startCreation().build())

        assertThat(firstView.getInt("id"), not(equalTo(secondView.getInt("id"))))
    }

    @Test
    fun shouldShowStartedTournament() {
        val startedView = create(startCreation().build())
        val shownView = tournaments
                .path("show")
                .queryParam("id", startedView.getInt("id"))
                .request()
                .get()
                .readEntity(JsonObject::class.java)

        assertThat(startedView, equalTo(shownView))
    }
}