package pl.dagguh.tournaments

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("health")
class HealthResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun checkHealth(): Health {
        return Health("HEALTHY")
    }

    data class Health(val status: String)
}
