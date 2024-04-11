package org.arobase.web.controller;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.arobase.domain.model.request.LanguageCreateRequest;
import org.arobase.infrastructure.persistence.entity.Language;
import org.arobase.infrastructure.persistence.service.BodyValidatorService;
import org.arobase.infrastructure.persistence.service.LanguageService;
import org.jboss.logging.Logger;

@Path("/api/v1/languages")
@Produces("application/json")
public class LanguageController {

    @Inject
    public BodyValidatorService bodyValidatorService;

    private final LanguageService languageService;

    private final Logger logger;

    public LanguageController(LanguageService languageService, Logger logger) {
        this.languageService = languageService;
        this.logger = logger;
    }

    @GET
    @Produces("application/json")
    public Uni<Response> listLanguages() {
        logger.info("GET /api/v1/languages called.");
        return Uni.createFrom().item(
                () -> Response.ok(languageService.findAllLanguages())
                        .build());
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getLanguageById(@PathParam("id") String id) {
        logger.info("GET /api/v1/languages/" + id + " called.");

        return Response.ok(languageService.findLanguageById(id)).build();
    }

    @POST
    @Consumes("application/json")
    public Language createLanguage(LanguageCreateRequest languageCreateRequest) {
        logger.info("POST /api/v1/languages for language" + languageCreateRequest.name() + " called.");

        bodyValidatorService.validateBody(languageCreateRequest);

        return languageService.createLanguage(languageCreateRequest);
    }

    @PATCH
    @Path("/{id}")
    @Consumes("application/json")
    public Response updateLanguage(@PathParam("id") String id, LanguageCreateRequest languageCreateRequest) {
        logger.info("PATCH /api/v1/languages/" + id + " called.");

        bodyValidatorService.validateBody(languageCreateRequest);

        return Response.ok(languageService.updateLanguage(id, languageCreateRequest)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteLanguage(@PathParam("id") String id) {
        logger.info("DELETE /api/v1/languages/" + id + " called.");

        languageService.deleteLanguage(id);

        return Response.ok("Language deleted successfully.").build();
    }

}
