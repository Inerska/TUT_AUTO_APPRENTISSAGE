package org.arobase.infrastructure.persistence.repository;

import org.arobase.infrastructure.persistence.entity.Exercice;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.quarkus.hibernate.reactive.panache.common.WithSession;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@WithSession
public class ExerciceRepository implements PanacheRepository<Exercice>{
}
