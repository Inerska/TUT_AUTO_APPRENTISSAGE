package org.arobase.infrastructure.persistence.repository;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.quarkus.hibernate.reactive.panache.common.WithSession;
import jakarta.enterprise.context.ApplicationScoped;
import org.arobase.infrastructure.persistence.entity.Account;

/**
 * The account repository.
 */
@ApplicationScoped
@WithSession
public class AccountRepository implements PanacheRepository<Account> {
}
