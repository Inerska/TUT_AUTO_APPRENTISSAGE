package org.arobase.infrastructure.authentication.service;

import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.smallrye.jwt.build.Jwt;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.unchecked.Unchecked;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.core.Response;
import org.arobase.infrastructure.exception.AuthentificationException;
import org.arobase.infrastructure.persistance.entity.Account;
import org.arobase.infrastructure.persistance.repository.AccountRepository;
import org.jboss.logging.Logger;

import java.util.Objects;

/**
 * The auth service.
 */
@Singleton
@WithSession
public class AuthService {

    /**
     * The logger.
     */
    @Inject
    Logger LOGGER;

    /**
     * The account repository.
     */
    @Inject
    AccountRepository accountRepository;

    /**
     * The hashed password service.
     */
    @Inject
    HashedPasswordService hashedPasswordService;


    /**
     * The login method.
     * @param account Account try to login.
     * @return The jwt token.
     */
    public Uni<String> login(Account account) {

        Objects.requireNonNull(account.getUsername(), "username field is required");
        Objects.requireNonNull(account.getPassword(), "password field is required");

        LOGGER.info(account.getUsername() + " try to login.");

        return accountRepository.find("username", account.getUsername())
                .firstResult()
                .onItem().ifNull().failWith(() -> new AuthentificationException(Response.Status.NOT_FOUND, "Account not found"))
                .onItem().transform(Unchecked.function(acc -> {

                        if(!hashedPasswordService.verifyPassword(account.getPassword(), acc.getPassword())){
                            throw new AuthentificationException(Response.Status.UNAUTHORIZED, "Wrong password");
                        }

                        return this.generateJwt(acc);
                }));

    }

    /**
     * The register method.
     * @param account Account try to register.
     * @return The jwt token.
     */
    public Uni<String> register(Account account) {

        Objects.requireNonNull(account.getUsername(), "username field is required");
        Objects.requireNonNull(account.getPassword(), "password field is required");
        Objects.requireNonNull(account.getConfirmPassword(), "confirmPassword field is required");
        Objects.requireNonNull(account.getName(), "name field is required");
        Objects.requireNonNull(account.getSurname(), "surname field is required");
        Objects.requireNonNull(account.getEmail(), "email field is required");

        LOGGER.info(account.getUsername() + " try to register.");

        Uni<Account> accountUni = accountRepository.find("username", account.getUsername())
                .firstResult()
                .onItem().ifNotNull().failWith(() -> new AuthentificationException(Response.Status.CONFLICT, "Account already exists"))
                .onItem().ifNull().switchTo(Unchecked.supplier(() -> {

                    if(!account.getPassword().equals(account.getConfirmPassword())){
                        throw new AuthentificationException(Response.Status.BAD_REQUEST, "Password and confirmPassword must be equals");
                    }

                    Account newAccount = new Account(account.getUsername(), hashedPasswordService.hashPassword(account.getPassword()), account.getName(), account.getSurname(), account.getEmail());
                    return accountRepository.persistAndFlush(newAccount);
                }));

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
