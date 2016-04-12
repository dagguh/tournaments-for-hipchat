package pl.dagguh.tournaments.tournament

import org.apache.logging.log4j.LogManager
import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("tournament")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
class TournamentResource {

    @Path("start")
    @POST
    fun start(): Response {
        LOG.info("Tournament started!")
        return Response.ok().build();
    }

    companion object {
        private val LOG = LogManager.getLogger(TournamentResource::class)
    }
}
