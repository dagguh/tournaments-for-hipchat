package pl.dagguh.tournaments.tournament

import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.core.Response.Status.NOT_FOUND

@Path("tournament")
@Produces(MediaType.APPLICATION_JSON)
class TournamentResource(private val dao: TournamentDao) {

    @Path("start")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    fun start(start: TournamentStartDto): Response {
        val view = dao.start(start)
        return Response.ok(view).build();
    }

    @Path("show")
    @GET
    fun show(@QueryParam("id") id: Long): Response {
        val view = dao.show(id)
        if (view.isPresent) {
            return Response.ok(view.get()).build();
        } else {
            return Response.status(NOT_FOUND).build();
        }
    }
}
