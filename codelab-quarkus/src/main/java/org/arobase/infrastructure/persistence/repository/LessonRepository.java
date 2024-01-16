package org.arobase.infrastructure.persistence.repository;

import org.arobase.infrastructure.persistence.entity.Lesson;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.quarkus.hibernate.reactive.panache.common.WithSession;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@WithSession
public class LessonRepository implements PanacheRepository<Lesson> {

}
