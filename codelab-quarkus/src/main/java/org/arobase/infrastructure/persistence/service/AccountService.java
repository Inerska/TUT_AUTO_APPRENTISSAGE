package org.arobase.infrastructure.persistence.service;

import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.QueryParam;
import org.arobase.infrastructure.persistence.entity.Account;
import org.arobase.infrastructure.persistence.repository.AccountRepository;

import java.util.List;

/**
 * The account service.
 */
@ApplicationScoped
@WithSession
public class AccountService {

    /**
     * The account repository.
     */
    private final AccountRepository accountRepository;

    /**
     * The constructor.
     * @param accountRepository The account repository.
     */
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * Get all accounts.
     * @return The list of accounts.
     */
    public Uni<List<Account>> getAccounts(){
        return accountRepository.findAll().list();
    }

    /**
     * Get an account by its id.
     * @param id The id of the account.
     * @return The account.
     */
    public Uni<Account> getAccountById(@QueryParam("id") Long id){
        return accountRepository.findById(id);
    }
}
