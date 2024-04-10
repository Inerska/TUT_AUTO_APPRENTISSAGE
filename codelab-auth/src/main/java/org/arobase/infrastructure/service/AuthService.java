package org.arobase.infrastructure.service;

import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.unchecked.Unchecked;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.arobase.infrastructure.dto.LoginCredentialsDTO;
import org.arobase.infrastructure.dto.RegisterCredentialsDTO;
import org.arobase.infrastructure.dto.TokensDTO;
import org.arobase.infrastructure.exception.AuthenticationException;
import org.arobase.infrastructure.persistance.entity.Account;
import org.arobase.infrastructure.persistance.repository.AccountRepository;
import org.arobase.infrastructure.service.api.ProfileAPIService;

/**
 * The auth service.
 */
@Singleton
@WithSession
@WithTransaction
@Transactional
public class AuthService {

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
     * The token management service.
     */
    @Inject
    TokenManagementService tokenManagementService;

    @Inject
    ProfileAPIService profileAPIService;

    /**
     * The login method.
     * @param loginCredentials The login credentials.
     * @return The jwt token.
     */
    public Uni<Account> login(LoginCredentialsDTO loginCredentials) {

        return accountRepository.find("mail", loginCredentials.mail())
                .firstResult()
                .onItem().ifNull().failWith(() -> new AuthenticationException(Response.Status.NOT_FOUND, "Account not found"))
                .onItem().ifNotNull().transformToUni(Unchecked.function(account -> {

                    if (!hashedPasswordService.verifyPassword(loginCredentials.password(), account.getPassword())) {
                        throw new AuthenticationException(Response.Status.UNAUTHORIZED, "Invalid password");
                    }

                    TokensDTO tokens = tokenManagementService.getTokens(loginCredentials.mail());

                    account.setAccessToken(tokens.accessToken());
                    account.setRefreshToken(tokens.refreshToken());

                    return accountRepository.persistAndFlush(account);
                }));
    }

    /**
     * The register method.
     * @param registerCredentials The register credentials.
     * @return The jwt token.
     */
    public Uni<Account> register(RegisterCredentialsDTO registerCredentials) {
        return accountRepository.find("mail", registerCredentials.mail()).firstResult()
                .onItem().ifNotNull().failWith(() -> new AuthenticationException(Response.Status.CONFLICT, "Account already exists"))
                .onItem().ifNull().switchTo(Unchecked.supplier(() -> {

                    if(!registerCredentials.password().equals(registerCredentials.confirmPassword())){
                        throw new AuthenticationException(Response.Status.BAD_REQUEST, "Passwords do not match");
                    }

                    String profileId = profileAPIService.createProfile(registerCredentials.username());

                    String hashedPassword = hashedPasswordService.hashPassword(registerCredentials.password());
                    TokensDTO tokens = tokenManagementService.getTokens(registerCredentials.mail());

                    Account newAccount = new Account(registerCredentials.mail(), hashedPassword, profileId, tokens.accessToken(), tokens.refreshToken());

                    return accountRepository.persistAndFlush(newAccount);
                }));
    }







}
