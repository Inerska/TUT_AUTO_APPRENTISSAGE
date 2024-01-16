package org.arobase.infrastructure.persistence.service;

import java.util.List;

import org.arobase.infrastructure.persistence.entity.Exercice;
import org.arobase.infrastructure.persistence.repository.ExerciceRepository;

import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@WithSession
public class ExerciceService {
    private final ExerciceRepository exerciceRepository;

    public ExerciceService(ExerciceRepository exerciceRepository) {
        this.exerciceRepository = exerciceRepository;
    }

    public Uni<List<Exercice>> getExercices(){
        return exerciceRepository.findAll().list();
    }

    public Uni<Exercice> getExerciceById(Long id){
        return exerciceRepository.findById(id);
    }
}
