package org.arobase.infrastructure.persistence.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;
import org.arobase.domain.model.ExerciceSubmitRequest;
import org.arobase.infrastructure.persistence.entity.ExerciceResults;
import org.arobase.infrastructure.persistence.repository.ExerciceResultsRepository;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import java.util.Optional;

@ApplicationScoped
public final class ExerciceService {
    private final ExerciceResultsRepository exerciceResultsRepository;

    @Channel("exercice-submitted")
    Emitter<ExerciceSubmitRequest> exerciceEmitter;

    public ExerciceService(ExerciceResultsRepository exerciceResultsRepository) {
        this.exerciceResultsRepository = exerciceResultsRepository;
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
        exerciceResult.persist();

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
        final var exerciceResult = exerciceResultsRepository.findByIdOptional(new ObjectId(id))
                .orElseThrow(() -> new NotFoundException("Exercice result not found."));

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
        final var exerciceResult = exerciceResultsRepository.findByIdOptional(new ObjectId(id))
                .orElseThrow(() -> new NotFoundException("Exercice result not found."));

        exerciceResult.status = status;
        exerciceResult.result = result;
        exerciceResult.persistOrUpdate();
    }
}
