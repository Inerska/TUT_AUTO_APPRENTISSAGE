package org.arobase.web.controller;

import java.util.List;

import org.arobase.infrastructure.persistence.entity.Exercice;
import org.arobase.infrastructure.persistence.repository.ExerciceRepository;
import org.jboss.logging.Logger;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/v1/exercices")
@Produces(MediaType.APPLICATION_JSON)
public class ExerciceController {

    private final ExerciceRepository exerciceRepository;
    private final Logger logger;

    public ExerciceController(ExerciceRepository exerciceRepository, Logger logger) {
        this.exerciceRepository = exerciceRepository;
        this.logger = logger;
    }

    @GET
    public Uni<List<Exercice>> getExercices() {

        logger.debugf("Get all exercices");

        return exerciceRepository.listAll();
    }

    @GET
    @Path("/{id}")
    public Uni<Response> getExerciceById(@QueryParam("id") Long id) {
        return exerciceRepository.findById(id)
                .onItem().ifNotNull().transform(exercice -> {
                    this.logger.debugf("Found exercice: %s", exercice);
                    return Response.ok(exercice).build();
                })
                .onItem().ifNull().continueWith(() -> {
                    this.logger.debugf("No exercice found with id: %s", id);
                    return Response.status(Response.Status.NOT_FOUND).build();
                });
    }
}
