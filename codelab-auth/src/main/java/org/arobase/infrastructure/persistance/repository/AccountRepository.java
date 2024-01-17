package org.arobase.infrastructure.persistance.repository;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.arobase.infrastructure.persistance.entity.Account;

@ApplicationScoped
public class AccountRepository implements PanacheRepository<Account> {
}
