package org.arobase.web.controller;

import io.vertx.core.json.JsonObject;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.arobase.domain.model.request.ExerciceCreateRequest;
import org.arobase.domain.model.request.ExerciceSubmitRequest;
import org.arobase.infrastructure.persistence.service.BodyValidatorService;
import org.arobase.infrastructure.persistence.service.ExerciceService;
import org.jboss.logging.Logger;

@Path("/api/v1/exercices")
@Produces(MediaType.APPLICATION_JSON)
public class ExerciceController {

    @Inject
    BodyValidatorService bodyValidatorService;
    private final ExerciceService exerciceService;
    private final Logger logger;

    public ExerciceController(ExerciceService exerciceService, Logger logger) {
        this.exerciceService = exerciceService;
        this.logger = logger;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response submitExercice(ExerciceSubmitRequest exerciceSubmitRequest) {

        logger.info("POST /api/v1/exercices/ for language" + exerciceSubmitRequest.getLanguage() + " called.");

        final var exerciceResultObjectId = exerciceService.submitExercice(exerciceSubmitRequest);
        final var response = new JsonObject();
        response.put("id", exerciceResultObjectId.toString());
        response.put("feedback", "Exercice submitted successfully.");

        return Response.ok(response).build();
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createExercice(ExerciceCreateRequest exerciceCreateRequest) {

        logger.info("POST /api/v1/exercices/create for language" + exerciceCreateRequest.getLanguage().name + " called.");

        bodyValidatorService.validateBody(exerciceCreateRequest);

        return exerciceService.createExercice(exerciceCreateRequest);
    }

    @GET
    @Path("/{id}/results")
    public Response getExerciceResultById(@PathParam("id") String id) {
        logger.info("GET /api/v1/exercices/" + id + "/results called.");

        final var exerciceResult = exerciceService.getExerciceResultById(id)
                .orElseThrow(() -> new NotFoundException("Exercice result not found."));

        return Response.ok(exerciceResult).build();
    }

    @GET
    @Path("/{id}")
    public Response getExerciceById(@PathParam("id") String id) {
        logger.info("GET /api/v1/exercices/" + id + " called.");

        final var exerciceResult = exerciceService.getExerciseById(id)
                .orElseThrow(() -> new NotFoundException("Exercice not found."));

        return Response.ok(exerciceResult).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteExercice(@PathParam("id") String id) {
        logger.info("DELETE /api/v1/exercices/" + id + " called.");

        exerciceService.deleteExerciceById(id);

        return Response.ok("Exercice deleted successfully.").build();
    }

}
