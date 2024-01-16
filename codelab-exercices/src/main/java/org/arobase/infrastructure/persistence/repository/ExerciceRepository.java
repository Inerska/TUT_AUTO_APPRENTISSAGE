package org.arobase.infrastructure.persistence.repository;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.arobase.infrastructure.persistence.entity.Exercice;

@ApplicationScoped
public class ExerciceRepository implements PanacheMongoRepository<Exercice> {
}
