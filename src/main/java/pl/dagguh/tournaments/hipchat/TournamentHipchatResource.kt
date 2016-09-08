package pl.dagguh.tournaments.hipchat

import org.apache.logging.log4j.LogManager
import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("hipchat")
class TournamentHipchatResource(private val dispatcher: HipchatCommandDispatcher) {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun commandViaHipChat(command: HipchatCommandDto): Response {
        LOG.info("Dispatching {}", command)
        return dispatcher.dispatch(command)
    }

    @POST
    @Path("installed")
    @Consumes(MediaType.APPLICATION_JSON)
    fun addonInstalled(installationData: HipchatInstallationDto): Response {
        println(installationData);
        //store the installation data
        //get capabilities and store urls
        return Response.ok().build();
    }

    companion object {
        private val LOG = LogManager.getLogger(TournamentHipchatResource::class)
    }
}

