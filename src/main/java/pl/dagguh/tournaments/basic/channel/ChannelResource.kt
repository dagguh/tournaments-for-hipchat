package pl.dagguh.tournaments.basic.channel

import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("channel")
@Produces(MediaType.APPLICATION_JSON)
class ChannelResource(
        private val channelDao: ChannelDao
) {

    @POST
    fun create(): Response {
        val channel = channelDao.create("generic")
        return Response.ok(channel).build()
    }
}
