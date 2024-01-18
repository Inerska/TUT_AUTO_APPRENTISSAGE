package org.arobase.infrastructure.authentication.service;

import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.smallrye.jwt.build.Jwt;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.arobase.infrastructure.persistance.entity.Account;
import org.arobase.infrastructure.persistance.repository.AccountRepository;

/**
 * The auth service.
 */
@Singleton
@WithSession
public class AuthService {

    /**
     * The account repository.
     */
    @Inject
    AccountRepository accountRepository;

    /**
     * The login method.
     * @param username The username of the account.
     * @return The jwt token.
     */
    public Uni<String> login(String username) {
        return accountRepository.find("username", username)
                .firstResult()
                .onItem().ifNull().failWith(() -> new RuntimeException("Account not found"))
                .onItem().transform(this::generateJwt);
    }

    /**
     * The register method.
     * @param username The username of the account.
     * @param password The password of the account.
     * @return The jwt token.
     */
    public Uni<String> register(String username, String password) {
        Uni<Account> accountUni = accountRepository.find("username", username)
                .firstResult()
                .onItem().ifNotNull().failWith(() -> new RuntimeException("Account already exists"))
                .onItem().ifNull().continueWith(() -> {
                    Account account = new Account(username, password);
                    accountRepository.persistAndFlush(account);
                    return account;
                });
        return accountUni.onItem().transform(this::generateJwt);
    }

    /**
     * The generateJwt method.
     * @param account The account.
     * @return The jwt token.
     */
    private String generateJwt(Account account){
        return Jwt.upn(account.getUsername())
                .claim("account", account)
                .sign();
    }



}
