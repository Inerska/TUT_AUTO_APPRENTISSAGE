package org.arobase.web;

import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.smallrye.jwt.build.Jwt;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.arobase.infrastructure.persistance.entity.Account;
import org.arobase.infrastructure.persistance.repository.AccountRepository;

@Singleton
@WithSession
public class AuthService {

    @Inject
    AccountRepository accountRepository;

    public Uni<String> login(String username) {
        return accountRepository.find("username", username)
                .firstResult()
                .onItem().ifNull().failWith(() -> new RuntimeException("Account not found"))
                .onItem().transform(this::generateJwt);
    }

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

    private String generateJwt(Account account){
        return Jwt.upn(account.getUsername())
                .claim("account", account)
                .sign();
    }



}
