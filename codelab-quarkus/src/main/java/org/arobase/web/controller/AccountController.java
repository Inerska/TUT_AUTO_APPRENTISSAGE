package org.arobase.web.controller;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.arobase.infrastructure.persistence.entity.Account;
import org.arobase.infrastructure.persistence.service.AccountService;

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
    private final AccountService accountService;

    /**
     * The constructor.
     * @param accountService The account service.
     */
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Get all accounts.
     * @return The list of accounts.
     */
    @GET
    public Uni<List<Account>> getAccounts(){
        return accountService.getAccounts();
    }

    /**
     * Get an account by its id.
     * @param id The id of the account.
     * @return The account.
     */
    @GET
    @Path("/{id}")
    public Uni<Account> getAccountById(Long id){
        return accountService.getAccountById(id);
    }

}
