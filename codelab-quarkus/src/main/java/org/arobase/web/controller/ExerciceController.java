package org.arobase.web.controller;

import java.util.List;

import org.arobase.infrastructure.persistence.entity.Exercice;
import org.arobase.infrastructure.persistence.service.ExerciceService;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/api/v1/exercices")
@Produces(MediaType.APPLICATION_JSON)
public class ExerciceController {

    private final ExerciceService exerciceService;

    public ExerciceController(ExerciceService exerciceService) {
        this.exerciceService = exerciceService;
    }

    @GET
    public Uni<List<Exercice>> getExercices() {
        return exerciceService.getExercices();
    }

    @GET
    @Path("/{id}")
    public Uni<Exercice> getExerciceById(Long id) {
        return exerciceService.getExerciceById(id);
    }
}
