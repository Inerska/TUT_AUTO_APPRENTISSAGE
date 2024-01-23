package org.arobase.web.controller;

import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.smallrye.mutiny.Uni;
import io.vertx.core.json.JsonObject;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.arobase.infrastructure.authentication.service.AuthService;
import org.arobase.infrastructure.persistance.entity.Account;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

/**
 * The auth controller.
 */
@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthController {

    /**
     * The auth service.
     */
    @Inject
    AuthService authService;

    /**
     * The json web token.
     */
    @Inject
    JsonWebToken jwt;

    /**
     * The register method.
     * @param account The user credential.
     * @return The jwt token.
     */
    @POST
    @Path("/register")
    @PermitAll
    @WithSession
    public Uni<Response> register(final Account account) {
        return authService.register(account)
                .map(jwt -> Response.ok().entity(new JsonObject().put("token", jwt)).build());
    }

    /**
     * The login method.
     * @param account The user credential.
     * @return The jwt token.
     */
    @POST
    @Path("/login")
    @PermitAll
    @WithSession
    public Uni<Response> login(final Account account) {
        return authService.login(account)
                .map(jwt -> Response.ok().entity(new JsonObject().put("token", jwt)).build());
    }

    /**
     * The method.
     * @return The username of the account.
     */
    @GET
    @Path("/me")
    @RolesAllowed({"USER", "ADMIN"})
    @Produces(MediaType.APPLICATION_JSON)
    public String me() {
        try {
            return jwt.getClaim("account");
        } catch (Exception e) {
            throw new InternalServerErrorException(e);
        }
    }

    /**
     * The admin method.
     * @return The username of the account.
     */
    @GET
    @Path("/admin")
    @RolesAllowed("ADMIN")
    @Produces(MediaType.TEXT_PLAIN)
    public String admin() {
        return "Hello Admin!";
    }


}
