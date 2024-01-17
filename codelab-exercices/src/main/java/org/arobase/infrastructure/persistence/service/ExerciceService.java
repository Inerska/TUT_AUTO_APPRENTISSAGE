package org.arobase.infrastructure.persistence.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.arobase.infrastructure.persistence.repository.ExerciceRepository;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@ApplicationScoped
public final class ExerciceService {
    private final ExerciceRepository exerciceRepository;

    @Channel("exercice-submitted")
    Emitter<String> exerciceEmitter;

    public ExerciceService(ExerciceRepository exerciceRepository) {
        this.exerciceRepository = exerciceRepository;
    }

    public void submitExercice(String exercice) {
        exerciceEmitter.send(exercice);
    }
}
