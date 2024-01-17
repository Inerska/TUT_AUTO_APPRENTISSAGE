package org.arobase.web.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.arobase.domain.model.ExerciceRequest;
import org.arobase.infrastructure.persistence.entity.ExerciceResults;
import org.arobase.infrastructure.persistence.service.ExerciceService;
import org.jboss.logging.Logger;

import java.util.List;

@Path("/api/v1/exercices")
@Produces(MediaType.APPLICATION_JSON)
public class ExerciceController {

    private final ExerciceService exerciceService;
    private final Logger logger;

    public ExerciceController(ExerciceService exerciceService, Logger logger) {
        this.exerciceService = exerciceService;
        this.logger = logger;
    }

    @POST
    public Response submitExercice(ExerciceRequest exerciceRequest) {
        logger.info("POST /api/v1/exercices/ for language" + exerciceRequest.getLanguage() + " called.");

        final var exerciceResultObjectId = exerciceService.submitExercice(exerciceRequest);
        final var response = new JsonObject();
        response.put("id", exerciceResultObjectId.toString());
        response.put("feedback", "Exercice submitted successfully.");

        return Response.ok(response).build();
    }

    @GET
    @Path("/{id}/results")
    public Response getExerciceResultById(@PathParam("id") String id) {
        logger.info("GET /api/v1/exercices/" + id + "/results called.");

        final var exerciceResult = exerciceService.getExerciceResultById(id)
                .orElseThrow(() -> new NotFoundException("Exercice result not found."));

        return Response.ok(exerciceResult).build();
    }
}
