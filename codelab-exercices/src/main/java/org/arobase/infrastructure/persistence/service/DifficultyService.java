package org.arobase.infrastructure.persistence.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.arobase.domain.model.request.DifficultyCreateRequest;
import org.arobase.infrastructure.persistence.entity.Difficulty;
import org.arobase.infrastructure.persistence.repository.DifficultyRepository;
import org.bson.types.ObjectId;
import org.jboss.logging.Logger;

@ApplicationScoped
public class DifficultyService {

    private final DifficultyRepository difficultyRepository;

    private final Logger logger;

    public DifficultyService(DifficultyRepository difficultyRepository, Logger logger) {
        this.difficultyRepository = difficultyRepository;
        this.logger = logger;
    }

    /**
     * Create a new difficulty.
     *
     * @param difficulty the difficulty to create
     * @return the created difficulty
     */
    public Difficulty createDifficulty(DifficultyCreateRequest difficulty) {
        final var df = new Difficulty();
        df.name = difficulty.name();

        df.persist();
        return df;
    }

    /**
     * Update a difficulty.
     *
     * @param difficulty the difficulty to update
     * @return the updated difficulty
     */
    public Difficulty updateDifficulty(String id, DifficultyCreateRequest difficulty) {
        final var df = difficultyRepository.findByIdOptional(new ObjectId(id))
                .orElseThrow(() -> new RuntimeException("Difficulty not found"));
        df.name = difficulty.name();

        df.persistOrUpdate();
        return df;
    }

    /**
     * Delete a difficulty.
     *
     * @param id the difficulty id
     */
    public void deleteDifficulty(String id) {
        final var df = difficultyRepository.findByIdOptional(new ObjectId(id))
                .orElseThrow(() -> new RuntimeException("Difficulty not found"));
        df.delete();
    }

    /**
     * Find a difficulty by its id.
     *
     * @param id the difficulty id
     * @return the difficulty
     */
    public Difficulty findDifficultyById(String id) {
        return difficultyRepository.findByIdOptional(new ObjectId(id))
                .orElseThrow(() -> new RuntimeException("Difficulty not found"));
    }

    /**
     * Find all difficulties.
     *
     * @return the list of difficulties
     */
    public Iterable<Difficulty> findAllDifficulties() {
        return difficultyRepository.listAll();
    }

}
