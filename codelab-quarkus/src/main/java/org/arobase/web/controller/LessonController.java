package org.arobase.web.controller;

import java.util.List;

import org.arobase.infrastructure.persistence.entity.Lesson;
import org.arobase.infrastructure.persistence.repository.LessonRepository;
import org.jboss.logging.Logger;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/v1/lessons")
@Produces(MediaType.APPLICATION_JSON)
public class LessonController {
    private final LessonRepository lessonRepository;
    private final Logger logger;

    public LessonController(LessonRepository lessonRepository, Logger logger) {
        this.lessonRepository = lessonRepository;
        this.logger = logger;
    }

    /**
     * Get all lessons.
     * 
     * @return The list of lessons.
     */
    @GET
    public Uni<List<Lesson>> getLessons() {

        logger.debugf("Get all lessons");

        return lessonRepository.listAll();
    }

    /**
     * Get a lesson by its id.
     * 
     * @param id The id of the lesson.
     * @return The lesson.
     */
    @GET
    @Path("/{id}")
    public Uni<Response> getLessonById(@QueryParam("id") Long id) {
        return lessonRepository.findById(id)
                .onItem().ifNotNull().transform(lesson -> {
                    this.logger.debugf("Found lesson: %s", lesson);
                    return Response.ok(lesson).build();
                })
                .onItem().ifNull().continueWith(() -> {
                    this.logger.debugf("No lesson found with id: %s", id);
                    return Response.status(Response.Status.NOT_FOUND).build();
                });
    }

}
