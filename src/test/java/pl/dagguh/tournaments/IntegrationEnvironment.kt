package pl.dagguh.tournaments

import org.glassfish.jersey.client.ClientConfig
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.client.WebTarget

class IntegrationEnvironment() {

    fun getRoot(): WebTarget {
        val clientConfig = ClientConfig()
        val client = ClientBuilder.newClient(clientConfig)
        return client.target("http://localhost:8080/")
    }
}