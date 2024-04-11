package org.arobase.domain.docker.service;

import io.smallrye.mutiny.Uni;
import org.arobase.domain.model.request.ExerciceSubmitRequest;

/**
 * Service for docker execution.
 */
public interface DockerExecutionService {

    /**
     * Execute the code.
     *
     * @param exerciceSubmitRequest the exercice request
     * @return the result
     */
    Uni<String> executeCode(final ExerciceSubmitRequest exerciceSubmitRequest);
}
