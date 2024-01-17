package org.arobase.domain.docker.service;

import io.smallrye.mutiny.Uni;
import org.arobase.domain.model.ExerciceRequest;

/**
 * Service for docker execution.
 */
public interface DockerExecutionService {

    /**
     * Execute the code.
     *
     * @param exerciceRequest the exercice request
     * @return the result
     */
    Uni<String> executeCode(final ExerciceRequest exerciceRequest);
}
