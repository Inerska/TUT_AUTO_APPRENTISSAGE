import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.core.Response;
import org.arobase.domain.model.request.LanguageCreateRequest;
import org.arobase.infrastructure.persistence.entity.Language;
import org.arobase.infrastructure.persistence.service.BodyValidatorService;
import org.arobase.infrastructure.persistence.service.LanguageService;
import org.arobase.web.controller.LanguageController;
import org.jboss.logging.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LanguageControllerTest {

    @Mock
    private LanguageService languageService;

    @Mock
    private BodyValidatorService bodyValidatorService;

    @Mock
    private Logger logger;

    private LanguageController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new LanguageController(languageService, logger);
        controller.bodyValidatorService = bodyValidatorService;
    }

    @Test
    void listLanguages_ReturnsLanguages() {
        when(languageService.findAllLanguages()).thenReturn(Collections.singletonList(new Language()));

        Uni<Response> response = controller.listLanguages();

        assertEquals(Response.Status.OK.getStatusCode(), response.await().indefinitely().getStatus());
        verify(logger, times(1)).info("GET /api/v1/languages called.");
    }

    @Test
    void getLanguageById_ReturnsLanguage() {
        String id = "1";
        when(languageService.findLanguageById(id)).thenReturn(new Language());

        Response response = controller.getLanguageById(id);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        verify(logger, times(1)).info("GET /api/v1/languages/" + id + " called.");
    }

    @Test
    void createLanguage_CreatesLanguage() {
        LanguageCreateRequest languageCreateRequest = new LanguageCreateRequest("English", "EN");
        Language createdLanguage = new Language();
        when(languageService.createLanguage(languageCreateRequest)).thenReturn(createdLanguage);

        Language result = controller.createLanguage(languageCreateRequest);

        assertEquals(createdLanguage, result);
        verify(logger, times(1)).info("POST /api/v1/languages for language" + languageCreateRequest.name() + " called.");
        verify(bodyValidatorService, times(1)).validateBody(languageCreateRequest);
    }

    @Test
    void updateLanguage_UpdatesLanguage() {
        String id = "1";
        LanguageCreateRequest languageCreateRequest = new LanguageCreateRequest("French", "FR");
        Language updatedLanguage = new Language();
        when(languageService.updateLanguage(id, languageCreateRequest)).thenReturn(updatedLanguage);

        Response response = controller.updateLanguage(id, languageCreateRequest);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        verify(logger, times(1)).info("PATCH /api/v1/languages/" + id + " called.");
        verify(bodyValidatorService, times(1)).validateBody(languageCreateRequest);
    }

    @Test
    void deleteLanguage_DeletesLanguage() {
        String id = "1";

        Response response = controller.deleteLanguage(id);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        verify(logger, times(1)).info("DELETE /api/v1/languages/" + id + " called.");
        verify(languageService, times(1)).deleteLanguage(id);
    }
}
