package org.arobase.infrastructure.persistence.service;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import org.arobase.domain.model.request.ExerciceCreateRequest;
import org.arobase.domain.model.request.ExerciceSubmitRequest;
import org.arobase.infrastructure.persistence.entity.Exercice;
import org.arobase.infrastructure.persistence.entity.ExerciceResults;
import org.arobase.infrastructure.persistence.repository.ExerciceRepository;
import org.arobase.infrastructure.persistence.repository.ExerciceResultsRepository;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public final class ExerciceService {
    private final ExerciceResultsRepository exerciceResultsRepository;
    private final ExerciceRepository exerciceRepository;
    private final Logger logger;

    @Channel("exercice-submitted")
    Emitter<ExerciceSubmitRequest> exerciceEmitter;

    public ExerciceService(ExerciceResultsRepository exerciceResultsRepository, ExerciceRepository exerciceRepository, Logger logger) {
        this.exerciceResultsRepository = exerciceResultsRepository;
        this.exerciceRepository = exerciceRepository;
        this.logger = logger;
    }

    /**
     * Submit an exercice and return the exercice result guid.
     *
     * @param exercice the exercice to submit
     * @return the exercice result object
     */
    public ObjectId submitExercice(ExerciceSubmitRequest exercice) {

        final var exerciceResult = new ExerciceResults();
        exerciceResult.status = "SUBMITTED";
        try {
            exerciceResult.persist();
        } catch (final Exception e) {
            logger.error("Error while persisting exercice result.", e);
            throw new RuntimeException("Error while persisting exercice result.");
        }

        exercice.setExerciceResultObjectId(exerciceResult.id.toString());

        exerciceEmitter.send(exercice);

        return exerciceResult.id;
    }


    /**
     * Get exercice results by object id.
     *
     * @param id the exercice result object id
     * @return the exercice result as an optional
     */
    public Optional<ExerciceResults> getExerciceResultById(final String id) {
        return exerciceResultsRepository.findByIdOptional(new ObjectId(id));
    }

    /**
     * Process exercice result by object id.
     *
     * @param id the exercice result object id
     */
    public void processExerciceResultById(final String id) {
        logger.info("Processing exercice result " + id + "...");
        final var exerciceResult = exerciceResultsRepository.findByIdOptional(new ObjectId(id)).orElseThrow(() -> new NotFoundException("Exercice result not found."));

        exerciceResult.status = "PROCESSING";
        exerciceResult.persistOrUpdate();
    }

    /**
     * Update exercice result by object id.
     *
     * @param id     the exercice result object id
     * @param status the exercice result status
     * @param result the exercice result
     */

    public void updateExerciceResult(final String id, final String status, final String result) {
        final var exerciceResult = exerciceResultsRepository.findByIdOptional(new ObjectId(id)).orElseThrow(() -> new NotFoundException("Exercice result not found."));

        exerciceResult.status = status;
        exerciceResult.result = result;
        exerciceResult.persistOrUpdate();
    }

    /**
     * Create an exercice.
     *
     * @param exerciceCreateRequest the exercice create request
     * @return the response
     */
    public Response createExercice(final ExerciceCreateRequest exerciceCreateRequest) {
        final var exercice = new Exercice();
        exercice.author = exerciceCreateRequest.getAuthor();
        exercice.testCode = exerciceCreateRequest.getTestCode();

        try {
            exercice.persist();

            return Response.ok(exercice.id).build();
        } catch (final Exception e) {
            logger.error("Error while creating exercice.", e);

            return Response.serverError().build();
        }
    }

    /**
     * Get test code by exercice id.
     *
     * @param id the exercice id
     * @return the test code
     */
    public String getTestCodeByExerciceId(final String id) {
        final var exercice = exerciceRepository.findByIdOptional(new ObjectId(id)).orElseThrow(() -> new NotFoundException("Exercice not found."));

        return exercice.testCode;
    }

    /**
     * Get exercices by language.
     * @param language the language
     * @return the list of exercices
     */
    public Uni<List<Exercice>> listExercicesByLanguage(final String language) {
        return Uni.createFrom().item(() -> exerciceRepository.list("language", language));
    }

}

