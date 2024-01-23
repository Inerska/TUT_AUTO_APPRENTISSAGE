package org.arobase.domain.docker.service;

import io.smallrye.mutiny.Uni;
import org.arobase.domain.model.request.ExerciceSubmitRequest;

/**
 * Defines a docker transaction command chain.
 *
 * @see DockerExecutionContext
 */
public interface DockerTransactionCommandChain {

    /**
     * Executes the command chain.
     *
     * @param context the docker execution context
     * @return the command chain result
     * @throws Exception if an error occurs
     */
    Uni<String> execute(final DockerExecutionContext context, final ExerciceSubmitRequest request) throws Exception;
}
