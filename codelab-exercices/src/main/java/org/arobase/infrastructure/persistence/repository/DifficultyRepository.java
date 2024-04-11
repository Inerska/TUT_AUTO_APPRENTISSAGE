package org.arobase.infrastructure.persistence.repository;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.arobase.infrastructure.persistence.entity.Difficulty;

import java.util.Optional;

@ApplicationScoped
public class DifficultyRepository implements PanacheMongoRepository<Difficulty> {
    public Optional<Difficulty> findByIdOptional(String s) {
        return find("_id", s).firstResultOptional();
    }
}
