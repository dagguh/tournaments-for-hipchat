package pl.dagguh.tournaments.hipchat

import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test
import pl.dagguh.tournaments.IntegrationEnvironment
import pl.dagguh.tournaments.PatternMatcher.Companion.matchesPattern
import java.io.StringReader
import javax.json.Json
import javax.json.Json.createObjectBuilder
import javax.json.JsonObject
import javax.json.JsonObjectBuilder
import javax.ws.rs.client.Entity
import javax.ws.rs.client.WebTarget
import javax.ws.rs.core.Response
import javax.ws.rs.core.Response.Status.OK

class TournamentHipchatResourceIT {

    val hipchat: WebTarget = IntegrationEnvironment().getRoot().path("hipchat")

    @Test
    fun shouldStartSwiss() {
        val response = command(buildCommand(
                "/tournament start swiss:2:PT65M:4 \"Netrunner Draft #5\" Alice Bob Carol Dave Eve Frank Grace"
        ).build())

        val message = response.readEntity(JsonObject::class.java).getString("message")
        assertThat(message, matchesPattern("Tournament T[\\d]* started\\.".toRegex()))
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


    private fun command(command: JsonObject): Response {
        return hipchat
                .path("webhook")
                .request()
                .post(Entity.json(command))
    }

    @Test
    fun shouldListInitialCommands() {
        val realPayload = """
{
    "event": "room_message",
    "item": {
        "message": {
            "date": "2016-09-09T08:23:02.932694+00:00",
            "from": {
                "id": 993998,
                "links": {
                    "self": "https://api.hipchat.com/v2/user/993998"
                },
                "mention_name": "KK",
                "name": "Kamil Kaczmarczyk [Atlassian]",
                "version": "AAUU1L12"
            },
            "id": "6b8d20a9-76f4-4ece-8186-956774abb949",
            "mentions": [],
            "message": "/tournament start",
            "type": "message"
        },
        "room": {
            "id": 2629908,
            "is_archived": false,
            "links": {
                "participants": "https://api.hipchat.com/v2/room/2629908/participant",
                "self": "https://api.hipchat.com/v2/room/2629908",
                "webhooks": "https://api.hipchat.com/v2/room/2629908/webhook"
            },
            "name": "Tournament AddOn",
            "privacy": "public",
            "version": "BCA7FE2B"
        }
    },
    "oauth_client_id": "0fa1bd87-5eb1-44a6-8148-dd2116d1c962",
    "webhook_id": 6071216
}
"""
        val reader = Json.createReader(StringReader(realPayload))
        val command = reader.readObject()
        reader.close()

        val response = command(command)

        assertThat(response.status, equalTo(OK.statusCode))
    }

    companion object {
        private val DUMMY_ROOM_ID = 40L
    }
}
