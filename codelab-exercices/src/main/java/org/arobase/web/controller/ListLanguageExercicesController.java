package org.arobase.web.controller;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.arobase.infrastructure.persistence.service.ExerciceService;

@Path("/api/v1/languages")
@Produces("application/json")
public class ListLanguageExercicesController {

    private final ExerciceService exerciceService;

    public ListLanguageExercicesController(ExerciceService exerciceService) {
        this.exerciceService = exerciceService;
    }

    @GET
    @Produces("application/json")
    @Path("/exercices")
    public Uni<Response> listExercises(@QueryParam("language") final String language) {

        if (language == null || language.isBlank()) {
            return Uni.createFrom().item(() -> Response.status(Response.Status.BAD_REQUEST)
                    .entity("Language parameter is required")
                    .build());
        }

        return exerciceService.listExercicesByLanguage(language)
                .onItem().transform(exercicesForLanguage -> {
                    if (exercicesForLanguage == null || exercicesForLanguage.isEmpty()) {
                        return Response.status(Response.Status.NOT_FOUND)
                                .entity("No exercices found for language " + language)
                                .build();
                    }
                    return Response.ok(exercicesForLanguage).build();
                })
                .onFailure().recoverWithItem(th -> Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("Error while fetching exercices for language " + language)
                        .build());
    }
}
