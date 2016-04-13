package pl.dagguh.tournaments

import org.apache.logging.log4j.LogManager
import javax.ws.rs.ServerErrorException
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper

class ServerErrorLogger : ExceptionMapper<ServerErrorException> {
    override fun toResponse(exception: ServerErrorException?): Response? {
        LOG.error("We've got a bug", exception)
        return Response.serverError().build()
    }

    companion object {
        private val LOG = LogManager.getLogger(ServerErrorLogger::class)
    }
}