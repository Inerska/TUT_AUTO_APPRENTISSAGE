package org.arobase.web.controller;

import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.tuples.Tuple2;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import org.arobase.infrastructure.docker.service.factory.DockerTransactionCommandChainFactory;

import java.util.List;

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
        return Uni.createFrom().item(() -> {
            try {
                List<Tuple2<String, String>> supportedLanguages = dockerTransactionCommandChainFactory.getSupportedLanguages();
                return Response.ok(supportedLanguages).build();
            } catch (Exception e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("Error while fetching supported languages")
                        .build();
            }
        });
    }
}