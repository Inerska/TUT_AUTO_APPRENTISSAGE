package org.arobase.infrastructure.messaging.processor;

import io.smallrye.reactive.messaging.annotations.Blocking;
import org.arobase.domain.messaging.processor.MessagingRequestProcessor;
import org.arobase.infrastructure.persistence.entity.Exercice;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.jboss.logging.Logger;

/**
 * Messaging request processor for {@link Exercice}.
 */
public final class ExerciceMessagingRequestProcessor implements MessagingRequestProcessor<String> {

    private final Logger logger;

    public ExerciceMessagingRequestProcessor(Logger logger) {
        this.logger = logger;
    }

    /**
     * {@inheritDoc}
     */
    @Incoming("exercice-submitted")
    @Blocking
    @Override
    public String process(String request) {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        logger.info("Exercice " + request + " processed.");

        //TODO: Send to the docker, get the result and return it
        return new String();
    }
}
