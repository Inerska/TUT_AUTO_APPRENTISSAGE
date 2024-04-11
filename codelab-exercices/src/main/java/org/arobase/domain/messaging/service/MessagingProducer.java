package org.arobase.domain.messaging.service;

public interface MessagingProducer<T> {
    void send(String queue, T message);
}
