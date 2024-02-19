package org.arobase.infrastructure.exception;

import io.vertx.core.json.JsonObject;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

/**
 * The authentification exception.
 */
public class AuthenticationException extends WebApplicationException {

    /**
     * The constructor.
     * @param status The status.
     * @param message The message.
     */
    public AuthenticationException(Response.Status status, String message) {
        super(Response.status(status)
                .entity(
                        new JsonObject()
                                .put("code", status.getStatusCode())
                                .put("message", message)
                )
                .build());
    }
}
