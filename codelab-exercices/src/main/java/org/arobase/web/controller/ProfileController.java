package org.arobase.web.controller;


import io.quarkus.mongodb.panache.PanacheMongoEntity;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.arobase.infrastructure.persistence.entity.Profile;
import org.arobase.infrastructure.persistence.service.ProfileService;

@Path("/api/v1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(final ProfileService profileService) {
        this.profileService = profileService;
    }

    @POST
    @Path("/profile")
    public Response createProfile(Profile profile) {
        PanacheMongoEntity entity = profileService.createProfile(profile.getUsername());
        return Response.ok(entity.id).build();
    }

    @GET
    @Path("/profile/{id}")
    public Response getProfileById(@PathParam("id") String id) {
        return Response.ok(profileService.getProfile(id)).build();
    }

}
