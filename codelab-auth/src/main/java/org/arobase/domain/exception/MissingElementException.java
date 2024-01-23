package org.arobase.domain.exception;

import io.vertx.core.json.JsonObject;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

/**
 * The missing element exception.
 */
public class MissingElementException extends WebApplicationException {

    /**
     * The constructor.
     * @param element The missing element.
     */
    public MissingElementException(String element) {
        super(Response.status(Response.Status.FORBIDDEN)
                .entity(new JsonObject().put("message", element + " is missing"))
                .build());
    }
}
