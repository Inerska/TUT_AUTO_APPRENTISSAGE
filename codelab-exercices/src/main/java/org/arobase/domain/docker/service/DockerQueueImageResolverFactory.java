package org.arobase.domain.docker.service;

import java.util.Optional;

/**
 * Represents a factory for docker queue image resolver.
 * <p>
 * It is used to resolve the appropriate docker image to use for a given language.
 */
public interface DockerQueueImageResolverFactory {

    /**
     * Resolve the docker queue image resolver for the given language.
     *
     * @param language the language
     * @return the docker queue image as an optional
     */
    Optional<String> resolve(final String language);
}
