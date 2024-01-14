package org.arobase.web.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.arobase.domain.service.greeting.GreetingService;

@Path("/hello")
public class GreetingController {

    private final GreetingService greetingService;

    @Inject

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return greetingService.greet("Alexis");
    }
}
