package org.arobase.domain.messaging.service;

import org.arobase.infrastructure.persistence.entity.Exercice;

public interface ExerciceMessagingProducer {
    public void send(String queue, Exercice exercice);
}
