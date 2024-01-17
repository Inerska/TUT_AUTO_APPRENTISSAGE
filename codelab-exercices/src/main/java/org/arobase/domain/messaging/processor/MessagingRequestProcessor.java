package org.arobase.domain.messaging.processor;

/**
 * Marker interface for messaging request processors.
 */
public interface MessagingRequestProcessor<T> {

    /**
     * Process the request and return the result.
     *
     * @param request the request
     * @return the result
     */
    T process(final String request);
}
