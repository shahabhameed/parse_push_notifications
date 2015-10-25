package com.folio3.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

@Path("/health")
@Component
@Produces(MediaType.APPLICATION_JSON)
public class HealthResource {

	@GET
    public String healthCheck() {
        return "Server Health : Fine";
    }
}
