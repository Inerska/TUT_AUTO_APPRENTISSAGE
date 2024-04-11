package org.arobase.domain.exception;

import io.vertx.core.json.JsonObject;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.ResponseStatus;

public class ProfileException extends WebApplicationException {

    public ProfileException(Response.Status status, String message) {
        super(Response.status(status)
                .entity(
                        new JsonObject()
                                .put("code", status.getStatusCode())
                                .put("message", message)
                )
                .build());
    }
}
