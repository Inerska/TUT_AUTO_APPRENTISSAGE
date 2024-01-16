package org.arobase.infrastructure.messaging.service;

import com.rabbitmq.client.Channel;
import org.arobase.domain.messaging.service.ExerciceMessagingProducer;
import org.arobase.infrastructure.persistence.entity.Exercice;

import java.io.IOException;

/**
 * Produce messages to RabbitMQ.
 */
public final class RabbitMQExerciceMessagingProducer implements ExerciceMessagingProducer {

    private final Channel channel;

    public RabbitMQExerciceMessagingProducer(Channel channel) {
        this.channel = channel;
    }

    @Override
    public void send(String queue, Exercice exercice) {
        try {
            channel.basicPublish("", queue, null, exercice.toString().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
