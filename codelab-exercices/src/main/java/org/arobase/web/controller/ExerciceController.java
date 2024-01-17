package org.arobase.web.controller;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.arobase.infrastructure.persistence.entity.Exercice;
import org.arobase.infrastructure.persistence.service.ExerciceService;
import org.jboss.logging.Logger;

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
    @Path("/{name: [a-zA-Z0-9]+}")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public void submitExercice(String name) {
        logger.info("POST /api/v1/exercices/" + name + " called.");

        exerciceService.submitExercice(name);
    }
}
