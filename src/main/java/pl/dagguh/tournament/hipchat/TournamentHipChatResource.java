package pl.dagguh.tournament.hipchat;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.dagguh.tournament.Health;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("hipchat")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TournamentHipChatResource {

    private static final Logger LOG = LogManager.getLogger(TournamentHipChatResource.class);

    @Path("health")
    @GET
    public Health checkHealth() {
        LOG.info("Health check!");
        Health health = new Health();
        health.status = "HEALTHY";
        return health;
    }

    @POST
    public Response commandViaHipChat() {
        return Response.ok().build();
    }

}
