package org.arobase.web.handler;

import io.vertx.core.json.JsonObject;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

/**
 * The global exception handler.
 */
@Provider
public class GlobalExceptionHandler implements ExceptionMapper<Throwable> {

    /**
     * The request context.
     */
    @Context
    private ContainerRequestContext requestContext;

    /**
     * Map an exception to a response.
     *
     * @param exception the exception to map to a response.
     * @return the response.
     */
    @Override
    public Response toResponse(Throwable exception) {

        System.out.println(requestContext.getUriInfo().getPath());

        if (requestContext.getUriInfo().getPath().startsWith("/auth")) {
            if (exception instanceof WebApplicationException) {
                return ((WebApplicationException) exception).getResponse();
            } else if (exception instanceof NullPointerException) {
                return Response.status(Response.Status.BAD_REQUEST.getStatusCode()).entity(new JsonObject().put("message", "Missing element")).build();
            }

        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()).entity(new JsonObject().put("message", "Internal Server Error")).build();

    }

}
