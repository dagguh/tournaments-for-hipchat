package pl.dagguh.tournaments.hipchat

import org.junit.Assert.assertThat
import org.junit.Test
import pl.dagguh.tournaments.IntegrationEnvironment
import pl.dagguh.tournaments.PatternMatcher.Companion.matchesPattern
import javax.json.Json.createObjectBuilder
import javax.json.JsonObject
import javax.json.JsonObjectBuilder
import javax.ws.rs.client.Entity
import javax.ws.rs.client.WebTarget

class TournamentHipchatResourceIT {

    val hipchat: WebTarget = IntegrationEnvironment().getRoot().path("hipchat")

    @Test
    fun shouldStartSwiss() {
        val actualResponse = command(buildCommand(
                "/tournament start swiss:2:PT65M:4 \"Netrunner Draft #5\" Alice Bob Carol Dave Eve Frank Grace"
        ).build())

        assertThat(actualResponse.getString("message"), matchesPattern("Tournament T[\\d]* started\\.".toRegex()))
    }


    private fun buildCommand(message: String): JsonObjectBuilder {
        return createObjectBuilder()
                .add("item", createObjectBuilder()
                        .add("message", createObjectBuilder()
                                .add("message", message)
                        )
                        .add("room", createObjectBuilder()
                                .add("id", DUMMY_ROOM_ID)
                        )
                )
    }

    private fun command(command: JsonObject): JsonObject {
        return hipchat
                .path("webhook")
                .request()
                .post(Entity.json(command))
                .readEntity(JsonObject::class.java)
    }

    companion object {
        private val DUMMY_ROOM_ID = 40L
    }
}
