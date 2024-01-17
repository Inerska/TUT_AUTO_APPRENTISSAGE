package org.arobase.infrastructure.messaging.processor;

import org.arobase.domain.model.ExerciceRequest;
import org.arobase.domain.docker.service.DockerExecutionService;
import org.arobase.domain.messaging.processor.MessagingRequestProcessor;
import org.arobase.infrastructure.persistence.entity.Exercice;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

/**
 * Messaging request processor for {@link Exercice}.
 */
public final class ExerciceMessagingRequestProcessor implements MessagingRequestProcessor<ExerciceRequest> {

    private final DockerExecutionService dockerExecutionService;
    private final Logger logger;

    public ExerciceMessagingRequestProcessor(DockerExecutionService dockerExecutionService, Logger logger) {
        this.dockerExecutionService = dockerExecutionService;
        this.logger = logger;
    }

    /**
     * {@inheritDoc}
     */
    @Incoming("exercice-submitted")
    @Override
    public void process(final ExerciceRequest request) {

        logger.info("Processing exercice " + request + "...");
        final var exerciceResponse = dockerExecutionService.executeCode(request);

        logger.info("Exercice " + request + " processed.");
    }
}
