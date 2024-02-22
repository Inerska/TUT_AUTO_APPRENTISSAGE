import io.smallrye.mutiny.tuples.Tuple2;
import jakarta.ws.rs.core.Response;
import org.arobase.infrastructure.docker.service.factory.DockerTransactionCommandChainFactory;
import org.arobase.web.controller.ListLanguagesController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Classe de test pour vérifier le comportement ListLanguagesController.
 */
public class ListLanguageControllerTest {

    @Mock
    DockerTransactionCommandChainFactory dockerTransactionCommandChainFactory;

    @InjectMocks
    ListLanguagesController controller;

    /**
     * Initialise les mocks avant chaque test.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Vérifie que le contrôleur retourne une réponse OK lorsqu'il trouve des langues supportées.
     */
    @Test
    void testListLanguages_ExercisesFound_ReturnsOk() {
        List<Tuple2<String, String>> supportedLanguages = Arrays.asList(
                Tuple2.of("python", "py"),
                Tuple2.of("java", "jav")
        );
        when(dockerTransactionCommandChainFactory.getSupportedLanguages()).thenReturn(supportedLanguages);

        Response response = controller.listLanguages().await().indefinitely();

        assertNotNull(response);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(supportedLanguages, response.getEntity());
    }

    /**
     * Vérifie que le contrôleur retourne une réponse Internal Server Error lorsque la recherche des langues supportées échoue.
     */
    @Test
    void testListLanguages_ExceptionThrown_ReturnsInternalServerError() {
        when(dockerTransactionCommandChainFactory.getSupportedLanguages())
                .thenThrow(new RuntimeException("Test exception"));

        Response response = controller.listLanguages().await().indefinitely();

        assertNotNull(response);
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        assertEquals("Error while fetching supported languages", response.getEntity());
    }

}
