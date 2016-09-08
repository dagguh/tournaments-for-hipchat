package pl.dagguh.tournaments.tournament

import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.core.Response.Status.NOT_FOUND

@Path("tournament")
@Produces(MediaType.APPLICATION_JSON)
class TournamentResource(private val service: TournamentService) {

    @Path("commands")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    fun listCommands(): Response {
        val commands = listOf(
                CommandViewDto("create"),
                CommandViewDto("set", listOf(
                        CommandViewDto("players"),
                        CommandViewDto("title")
                )),
                CommandViewDto("show"),
                CommandViewDto("start"),
                CommandViewDto("use", listOf(
                        CommandViewDto("swiss")
                ))
        )
        return Response.ok(commands).build()
    }

    @Path("start")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    fun start(start: TournamentStartDto): Response {
        val view = service.start(start)
        return Response.ok(view).build()
    }

    @Path("show")
    @GET
    fun show(@QueryParam("id") id: Long): Response {
        val view = service.show(id)
        if (view.isPresent) {
            return Response.ok(view.get()).build()
        } else {
            return Response.status(NOT_FOUND).build()
        }
    }
}
