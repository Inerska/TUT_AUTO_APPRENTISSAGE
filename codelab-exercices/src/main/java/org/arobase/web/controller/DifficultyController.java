package org.arobase.web.controller;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.arobase.domain.model.request.DifficultyCreateRequest;
import org.arobase.infrastructure.persistence.service.BodyValidatorService;
import org.arobase.infrastructure.persistence.service.DifficultyService;
import org.jboss.logging.Logger;

@Path("/api/v1/difficulties")
@Produces("application/json")
public class DifficultyController {

    @Inject
    BodyValidatorService bodyValidatorService;

    private final DifficultyService difficultyService;

    private final Logger logger;

    public DifficultyController(DifficultyService difficultyService, Logger logger) {
        this.difficultyService = difficultyService;
        this.logger = logger;
    }

    @GET
    @Produces("application/json")
    public Uni<Response> listDifficulties() {
        logger.info("GET /api/v1/difficulties called.");
        return Uni.createFrom().item(
                () -> Response.ok(difficultyService.findAllDifficulties())
                        .build());
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getDifficultyById(@PathParam("id") String id) {
        logger.info("GET /api/v1/difficulties/" + id + " called.");

        return Response.ok(difficultyService.findDifficultyById(id)).build();
    }

    @POST
    @Consumes("application/json")
    public Response createDifficulty(DifficultyCreateRequest difficultyCreateRequest) {

        logger.info("POST /api/v1/difficulties for difficulty" + difficultyCreateRequest.name() + " called.");

        bodyValidatorService.validateBody(difficultyCreateRequest);

        return Response.ok(difficultyService.createDifficulty(difficultyCreateRequest)).build();
    }

    @PATCH
    @Path("/{id}")
    @Consumes("application/json")
    public Response updateDifficulty(@PathParam("id") String id, DifficultyCreateRequest difficultyCreateRequest) {
        logger.info("PATCH /api/v1/difficulties for difficulty" + difficultyCreateRequest.name() + " called.");

        bodyValidatorService.validateBody(difficultyCreateRequest);

        return Response.ok(difficultyService.updateDifficulty(id, difficultyCreateRequest)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteDifficulty(@PathParam("id") String id) {
        logger.info("DELETE /api/v1/difficulties/" + id + " called.");

        difficultyService.deleteDifficulty(id);

        return Response.ok("Difficulty deleted successfully.").build();
    }

}
