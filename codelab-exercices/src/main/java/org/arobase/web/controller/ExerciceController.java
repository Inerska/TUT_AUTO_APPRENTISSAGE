package org.arobase.web.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.arobase.infrastructure.persistence.entity.Exercice;
import org.arobase.infrastructure.persistence.repository.ExerciceRepository;

import java.util.List;

@Path("/api/v1/exercices")
@Produces(MediaType.APPLICATION_JSON)
public class ExerciceController {

    private final ExerciceRepository exerciceRepository;

    public ExerciceController(ExerciceRepository exerciceRepository) {
        this.exerciceRepository = exerciceRepository;
    }

    @GET
    public List<Exercice> getAll() {
        return exerciceRepository.listAll();
    }
}
