package pl.dagguh.tournaments.basic.tournament

import pl.dagguh.tournaments.spi.TournamentCreationDto
import pl.dagguh.tournaments.spi.TournamentService
import pl.dagguh.tournaments.spi.TournamentStartDto
import pl.dagguh.tournaments.basic.CommandViewDto
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.core.Response.Status.NOT_FOUND

@Path("tournament")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
class TournamentResource(
        private val service: TournamentService
) {

    @Path("create")
    @POST
    fun create(creation: TournamentCreationDto): Response {
        val view = service.create(creation)
        return Response.ok(view).build()
    }

    @Path("commands")
    @GET
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
