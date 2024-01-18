package org.arobase.web.controller;

import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.smallrye.mutiny.Uni;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.arobase.infrastructure.authentication.service.AuthService;
import org.arobase.infrastructure.persistance.entity.UserCredential;
import org.eclipse.microprofile.jwt.JsonWebToken;

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
     * @param userCredential The user credential.
     * @return The jwt token.
     */
    @POST
    @Path("/register")
    @PermitAll
    @WithSession
    public Uni<String> register(UserCredential userCredential) {
        return authService.register(userCredential.getUsername(), userCredential.getPassword());
    }

    /**
     * The login method.
     * @param username The username of the account.
     * @return The jwt token.
     */
    @POST
    @Path("/login")
    @PermitAll
    @WithSession
    public Uni<String> login(String username) {
        return authService.login(username);
    }

    /**
     * The me method.
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
