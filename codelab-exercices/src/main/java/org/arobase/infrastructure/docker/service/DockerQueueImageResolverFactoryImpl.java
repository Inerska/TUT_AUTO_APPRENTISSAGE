package org.arobase.infrastructure.docker.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.arobase.domain.docker.service.DockerQueueImageResolverFactory;

import java.util.Optional;

@ApplicationScoped
public class DockerQueueImageResolverFactoryImpl implements DockerQueueImageResolverFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<String> resolve(final String language) {
        return switch (language) {
            case "java" -> Optional.of("openjdk:11");
            case "python" -> Optional.of("python:3.9");
            default -> Optional.empty();
        };
    }
}