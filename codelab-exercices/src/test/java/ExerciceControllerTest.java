import io.vertx.core.json.JsonObject;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import org.arobase.domain.model.request.ExerciceCreateRequest;
import org.arobase.domain.model.request.ExerciceSubmitRequest;
import org.arobase.infrastructure.persistence.entity.ExerciceResults;
import org.arobase.infrastructure.persistence.service.ExerciceService;
import org.arobase.web.controller.ExerciceController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.bson.types.ObjectId;

/**
 * Classe de test pour le contrôleur ExerciceController.
 */
class ExerciceControllerTest {

    @Mock
    private ExerciceService exerciceService;
    @InjectMocks
    private ExerciceController exerciceController;

    /**
     * Méthode exécutée avant chaque test.
     * Initialise les mocks et les instances nécessaires.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Teste la soumission d'un exercice avec une requête valide.
     * Devrait retourner un succès avec un ID et un message approprié.
     */
    @Test
    void submitExercice_ValidRequest_Success() {
        // Préparation des données nécessaires pour le test
        ExerciceSubmitRequest request = new ExerciceSubmitRequest("author", "testCode", "someData", "someOtherData");
        when(exerciceService.submitExercice(any())).thenReturn(new ObjectId("507f191e810c19729de860ea"));

        // Exécution de la méthode à tester
        Response response = exerciceController.submitExercice(request);

        // Vérification des résultats ou du comportement attendu
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        JsonObject jsonResponse = (JsonObject) response.getEntity();
        assertEquals("507f191e810c19729de860ea", jsonResponse.getString("id"));
        assertEquals("Exercice submitted successfully.", jsonResponse.getString("feedback"));
    }

    /**
     * Teste la création d'un exercice avec une requête valide.
     * Devrait retourner un succès.
     */
    @Test
    void createExercice_ValidRequest_Success() {
        // Préparation des données nécessaires pour le test
        ExerciceCreateRequest request = new ExerciceCreateRequest("author", "testCode", "language");
        when(exerciceService.createExercice(any())).thenReturn(Response.ok().build());

        // Exécution de la méthode à tester
        Response response = exerciceController.createExercice(request);

        // Vérification des résultats ou du comportement attendu
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    /**
     * Teste la récupération des résultats d'un exercice par ID avec un ID valide.
     * Devrait retourner un succès.
     */
    @Test
    void getExerciceResultById_ValidId_Success() {
        // Préparation des données nécessaires pour le test
        String id = "exampleId";
        when(exerciceService.getExerciceResultById(id)).thenReturn(Optional.of(new ExerciceResults()));

        // Exécution de la méthode à tester
        Response response = exerciceController.getExerciceResultById(id);

        // Vérification des résultats ou du comportement attendu
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    /**
     * Teste la récupération des résultats d'un exercice par ID avec un ID invalide.
     * Devrait lancer une NotFoundException.
     */
    @Test
    void getExerciceResultById_InvalidId_NotFound() {
        // Préparation des données nécessaires pour le test
        String id = "invalidId";
        when(exerciceService.getExerciceResultById(id)).thenReturn(Optional.empty());

        // Vérification que NotFoundException est levée lors de l'appel de la méthode
        assertThrows(NotFoundException.class, () -> exerciceController.getExerciceResultById(id));
    }
}
