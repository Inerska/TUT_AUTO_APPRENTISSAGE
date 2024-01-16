package org.arobase.web.controller;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.arobase.infrastructure.persistence.entity.Account;
import org.arobase.infrastructure.persistence.repository.AccountRepository;
import org.jboss.logging.Logger;

import java.util.List;

/**
 * The account controller.
 */
@Path("/api/v1/accounts")
@Produces(MediaType.APPLICATION_JSON)
public class AccountController {

    /**
     * The account service.
     */
    private final AccountRepository accountRepository;

    private final Logger logger;

    /**
     * The constructor.
     * 
     * @param accountService The account service.
     */
    public AccountController(AccountRepository accountRepository, Logger logger) {
        this.accountRepository = accountRepository;
        this.logger = logger;
    }

    /**
     * b
     * Get all accounts.
     * 
     * @return The list of accounts.
     */
    @GET
    public Uni<List<Account>> getAccounts() {

        logger.debugf("Get all accounts");

        return accountRepository.listAll();
    }

    /**
     * Get an account by its id.
     * 
     * @param id The id of the account.
     * @return The account.
     */
    @GET
    @Path("/{id}")
    public Uni<Response> getAccountById(@QueryParam("id") Long id) {
        return accountRepository.findById(id)
                .onItem().ifNotNull().transform(account -> {
                    this.logger.debugf("Found account: %s", account);
                    return Response.ok(account).build();
                })
                .onItem().ifNull().continueWith(() -> {
                    this.logger.debugf("No account found with id: %s", id);
                    return Response.status(Response.Status.NOT_FOUND).build();
                });
    }

}
