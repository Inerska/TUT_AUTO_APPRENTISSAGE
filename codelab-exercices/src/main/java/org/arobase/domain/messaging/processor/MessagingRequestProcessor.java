package org.arobase.domain.messaging.processor;

import org.arobase.domain.model.MessagingRequest;

/**
 * Messaging request processor.
 *
 * @param <TObjectRequest> the type of the request
 */
public interface MessagingRequestProcessor<TObjectRequest extends MessagingRequest> {

    /**
     * Process the request and return the result.
     *
     * @param request the request
     * @return the result
     */
    void process(final TObjectRequest request);
}
