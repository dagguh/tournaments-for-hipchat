package pl.dagguh.tournaments.hipchat

import org.hamcrest.CoreMatchers.equalTo
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
    fun shouldStartTournament() {
        val startResponse = command(buildCommand("/tournament start swiss:2:PT65M:4 Title Alice Bob").build())

        assertThat(startResponse.getString("message"), matchesPattern(START_RESPONSE_PATTERN))
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
                .request()
                .post(Entity.json(command))
                .readEntity(JsonObject::class.java)
    }

    @Test
    fun shouldStartAndShowTheNewTournament() {
        val startResponseMessage = command(buildCommand(
                "/tournament start swiss:2:PT65M:4 \"Netrunner Draft #5\" @Alice @Bob Carol @Dave @Eve Frank Grace"
        ).build()).getString("message")
        val tournamentId = START_RESPONSE_PATTERN
                .matchEntire(startResponseMessage)
                ?.groups
                ?.get(START_RESPONSE_PATTERN_ID_GROUP)
                ?.value
                ?.toLong()
        val showResponseMessage = command(buildCommand(
                "/tournament show T%d".format(tournamentId)
        ).build()).getString("message")

        assertThat(showResponseMessage, equalTo("""
            Tournament `T1` “Netrunner Draft #5” has started on 2016-05-16 17:35.

            It uses Swiss pairings. Number of rounds: 2. Time for each round: 65 minutes. Byes are worth 4 points.

            Participants: Alice Alpha, Bob Beta, Carol, Dave Delta, Eve Epsilon, Frank, Grace

            It is the current tournament.
        """))
    }

    companion object {
        private val DUMMY_ROOM_ID = 40L
        private val START_RESPONSE_PATTERN = "Tournament T([\\d]*) started\\.".toRegex()
        private val START_RESPONSE_PATTERN_ID_GROUP = 1
    }
}
