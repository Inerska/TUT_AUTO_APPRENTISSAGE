package org.arobase.web.controller;

import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.smallrye.mutiny.Uni;
import io.vertx.core.json.JsonObject;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.arobase.infrastructure.service.BodyValidatorService;
import org.arobase.infrastructure.dto.RegisterCredentialsDTO;
import org.arobase.infrastructure.service.AuthService;
import org.jboss.logging.Logger;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RegisterController {

    /**
     * The auth service.
     */
    @Inject
    AuthService authService;

    /**
     * The body validator service.
     */
    @Inject
    BodyValidatorService bodyValidatorService;


    /**
     * The register method.
     * @param registerCredentials The register credentials.
     * @return The jwt token.
     */
    @POST
    @Path("/register")
    @PermitAll
    @WithSession
    public Uni<Response> register(final RegisterCredentialsDTO registerCredentials) {
        bodyValidatorService.validateBody(registerCredentials);
        return authService.register(registerCredentials)
                .map(jwt -> Response.ok().entity(new JsonObject().put("token", jwt)).build());
    }
}
