package org.arobase.web.controller;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import org.arobase.infrastructure.docker.service.factory.DockerTransactionCommandChainFactory;

@Path("/api/v1/languages")
@Produces("application/json")
public class ListLanguagesController {

    private final DockerTransactionCommandChainFactory dockerTransactionCommandChainFactory;

    public ListLanguagesController(DockerTransactionCommandChainFactory dockerTransactionCommandChainFactory) {
        this.dockerTransactionCommandChainFactory = dockerTransactionCommandChainFactory;
    }

    @GET
    @Produces("application/json")
    public Uni<Response> listLanguages() {
        return Uni.createFrom().item(
                () -> Response.ok(dockerTransactionCommandChainFactory.getSupportedLanguages())
                        .build());
    }
}
