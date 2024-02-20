package org.arobase.infrastructure.service.api;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri = "http://exercices.codelab.local:8080/api/v1")
@Path("/profile/65d4a80560ce2b7f8766cad8")
public interface ProfileAPIService {

    @GET
    @Path("profile/65d4ce440cd10a2bd0afa4c3")
    @Produces(jakarta.ws.rs.core.MediaType.APPLICATION_JSON)
    Uni<String> getProfile();

}
