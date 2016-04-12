package pl.dagguh.tournaments

import org.apache.logging.log4j.LogManager
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("health")
class HealthResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun checkHealth(): Health {
        LOG.info("Health check!")
        return Health("HEALTHY")
    }

    data class Health(var status: String = "")

    companion object {
        private val LOG = LogManager.getLogger(HealthResource::class)
    }
}
