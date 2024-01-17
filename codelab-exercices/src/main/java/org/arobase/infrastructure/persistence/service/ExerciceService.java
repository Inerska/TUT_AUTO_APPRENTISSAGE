package org.arobase.infrastructure.persistence.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.arobase.domain.model.ExerciceRequest;
import org.arobase.infrastructure.persistence.entity.ExerciceResults;
import org.arobase.infrastructure.persistence.repository.ExerciceResultsRepository;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public final class ExerciceService {
    private final ExerciceResultsRepository exerciceResultsRepository;

    @Channel("exercice-submitted")
    Emitter<ExerciceRequest> exerciceEmitter;

    public ExerciceService(ExerciceResultsRepository exerciceResultsRepository) {
        this.exerciceResultsRepository = exerciceResultsRepository;
    }

    /**
     * Submit an exercice and return the exercice result guid.
     *
     * @param exercice the exercice to submit
     * @return the exercice result object
     */
    public ObjectId submitExercice(final ExerciceRequest exercice) {

        final var exerciceResult = new ExerciceResults();
        exerciceResult.status = "SUBMITTED";
        exerciceResult.persist();

        exerciceEmitter.send(exercice);

        return exerciceResult.id;
    }

    public Optional<ExerciceResults> getExerciceResultById(final String id) {
        return exerciceResultsRepository.findByIdOptional(new ObjectId(id));
    }
}
