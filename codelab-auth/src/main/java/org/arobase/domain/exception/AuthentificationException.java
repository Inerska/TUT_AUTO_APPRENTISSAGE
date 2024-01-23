package org.arobase.domain.exception;

import io.vertx.core.json.JsonObject;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

/**
 * The authentification exception.
 */
public class AuthentificationException extends WebApplicationException {

    /**
     * The constructor.
     * @param status The status.
     * @param message The message.
     */
    public AuthentificationException(Response.Status status, String message) {
        super(Response.status(status)
                .entity(new JsonObject().put("message", message))
                .build());
    }
}
