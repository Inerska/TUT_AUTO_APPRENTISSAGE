package org.arobase;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.arobase.service.GreetingService;

@Path("/hello")
public class ExampleResource {

    private final GreetingService greetingService;

    public ExampleResource(final GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return greetingService.greet("Samuel");
    }
}
