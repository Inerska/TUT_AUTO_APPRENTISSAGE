import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.core.Response;
import org.arobase.infrastructure.persistence.entity.Exercice;
import org.arobase.infrastructure.persistence.service.ExerciceService;
import org.arobase.web.controller.ListLanguageExercicesController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Classe de test pour vérifier le comportement de ListLanguageExercicesController.
 */
public class ListLanguageExercicesControllerTest {

    @Mock
    ExerciceService exerciceService;

    @InjectMocks
    ListLanguageExercicesController controller;

    /**
     * Initialise les mocks avant chaque test.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Vérifie que le contrôleur retourne une réponse OK lorsqu'il trouve des exercices pour une langue donnée.
     */
    @Test
    void testListExercises_ExercisesFound_ReturnsOk() {

        String language = "Java";
        List<Exercice> exercises = Collections.singletonList(new Exercice());
        when(exerciceService.listExercicesByLanguage(language)).thenReturn(Uni.createFrom().item(exercises));

        Uni<Response> response = controller.listExercises(language);

        assertNotNull(response);
        Response resp = response.await().indefinitely();
        assertNotNull(resp);
        assertEquals(Response.Status.OK.getStatusCode(), resp.getStatus());
        assertEquals(exercises, resp.getEntity());
    }

    /**
     * Vérifie que le contrôleur retourne une réponse NotFound lorsque aucun exercice n'est trouvé pour une langue donnée.
     */
    @Test
    void testListExercises_ExercisesNotFound_ReturnsNotFound() {

        String language = "Python";
        when(exerciceService.listExercicesByLanguage(language)).thenReturn(Uni.createFrom().item(Collections.emptyList()));

        Uni<Response> response = controller.listExercises(language);

        assertNotNull(response);
        Response resp = response.await().indefinitely();
        assertNotNull(resp);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), resp.getStatus());
        assertEquals("No exercices found for language " + language, resp.getEntity());
    }

    /**
     * Vérifie que le contrôleur retourne une réponse BadRequest lorsque la langue passée en paramètre est nulle.
     */
    @Test
    void testListExercises_NullLanguage_ReturnsBadRequest() {

        String language = null;

        Uni<Response> response = controller.listExercises(language);

        assertNotNull(response);
        Response resp = response.await().indefinitely();
        assertNotNull(resp);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), resp.getStatus());
        assertEquals("Language parameter is required", resp.getEntity());
    }

    /**
     * Vérifie que le contrôleur retourne une réponse InternalServerError lorsque une exception est levée lors de la recherche des exercices.
     */
    @Test
    void testListExercises_ExceptionThrown_ReturnsInternalServerError() {
        String language = "Java";
        when(exerciceService.listExercicesByLanguage(language)).thenReturn(Uni.createFrom().failure(new RuntimeException("Test exception")));

        Uni<Response> response = controller.listExercises(language);

        assertNotNull(response);
        Response resp = response.await().indefinitely();
        assertNotNull(resp);
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), resp.getStatus());
        assertEquals("Error while fetching exercices for language " + language, resp.getEntity());
    }
}
