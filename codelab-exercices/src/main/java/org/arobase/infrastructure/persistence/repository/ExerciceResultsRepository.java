package org.arobase.infrastructure.persistence.repository;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.arobase.infrastructure.persistence.entity.ExerciceResults;

@ApplicationScoped
public class ExerciceResultsRepository implements PanacheMongoRepository<ExerciceResults> {
}
