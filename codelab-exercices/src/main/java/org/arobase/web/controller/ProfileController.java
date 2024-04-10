package org.arobase.web.controller;


import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.vertx.core.json.JsonObject;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.arobase.domain.dto.ProfileDTO;
import org.arobase.infrastructure.persistence.service.ProfileService;
import org.arobase.infrastructure.service.BodyValidatorService;

@Path("/api/v1/profil")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProfileController {

    @Inject
    ProfileService profileService;
    @Inject
    BodyValidatorService bodyValidatorService;

    @POST
    public Response createProfile(final ProfileDTO profile) {
        bodyValidatorService.validateBody(profile);
        PanacheMongoEntity entity = profileService.createProfile(profile.username());
        return Response.ok().entity(new JsonObject().put("profile-id", entity.id)).build();
    }

    @GET
    @Path("/{id}")
    public Response getProfileById(@PathParam("id") String id) {
        return Response.ok(profileService.getProfile(id)).build();
    }

}
