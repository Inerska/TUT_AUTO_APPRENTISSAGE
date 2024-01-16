package org.arobase.infrastructure.persistence.repository;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.quarkus.hibernate.reactive.panache.common.WithSession;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@WithSession
public class LessonRepository implements PanacheRepository<LessonRepository> {

}
