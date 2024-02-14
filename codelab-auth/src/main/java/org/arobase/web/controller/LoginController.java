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
import org.arobase.infrastructure.dto.LoginCredentialsDTO;
import org.arobase.infrastructure.service.AuthService;
import org.arobase.infrastructure.service.BodyValidatorService;

/**
 * The login controller.
 */
@Path("/auth/login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginController {

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
     * The login method.
     * @param loginCredentials The login credentials.
     * @return The jwt token.
     */
    @POST
    @PermitAll
    @WithSession
    public Uni<Response> login(final LoginCredentialsDTO loginCredentials) {
        bodyValidatorService.validateBody(loginCredentials);
        return authService.login(loginCredentials)
                .map(account -> Response.ok().entity(
                        new JsonObject()
                                .put("access-token", account.getAccessToken())
                                .put("refresh-token", account.getRefreshToken())
                ).build());
    }
}
