package org.arobase.infrastructure.messaging.processor;

import org.arobase.domain.docker.service.DockerExecutionService;
import org.arobase.domain.messaging.processor.MessagingRequestProcessor;
import org.arobase.domain.model.ExerciceRequest;
import org.arobase.infrastructure.persistence.entity.Exercice;
import org.arobase.infrastructure.persistence.service.ExerciceService;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

/**
 * Messaging request processor for {@link Exercice}.
 */
public final class ExerciceMessagingRequestProcessor implements MessagingRequestProcessor<ExerciceRequest> {

    private final ExerciceService exerciceService;
    private final DockerExecutionService dockerExecutionService;
    private final Logger logger;

    public ExerciceMessagingRequestProcessor(ExerciceService exerciceService, DockerExecutionService dockerExecutionService, Logger logger) {
        this.exerciceService = exerciceService;
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

        exerciceService.processExerciceResultById(request.getExerciceResultObjectId());

        dockerExecutionService.executeCode(request).subscribe().with(exerciceResponse -> {
            exerciceService.updateExerciceResult(request.getExerciceResultObjectId(), "COMPLETED", exerciceResponse);
            logger.info("Exercice " + request + " processed.");
        }, failure -> {
            exerciceService.updateExerciceResult(request.getExerciceResultObjectId(), "ERROR", failure.getMessage());
            logger.error("Error processing exercice " + request, failure);
        });
    }


}
