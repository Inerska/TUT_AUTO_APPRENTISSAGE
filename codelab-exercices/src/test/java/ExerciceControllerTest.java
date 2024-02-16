import io.vertx.core.json.JsonObject;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import org.arobase.domain.model.request.ExerciceCreateRequest;
import org.arobase.domain.model.request.ExerciceSubmitRequest;
import org.arobase.infrastructure.persistence.entity.ExerciceResults;
import org.arobase.infrastructure.persistence.service.ExerciceService;
import org.arobase.web.controller.ExerciceController;
import org.arobase.infrastructure.persistence.entity.ExerciceResults;

import java.util.Optional;

import org.jboss.logging.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.bson.types.ObjectId;

class ExerciceControllerTest {

    @Mock
    private ExerciceService exerciceService;

    @Mock
    private Logger logger;

    @InjectMocks
    private ExerciceController exerciceController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void submitExercice_ValidRequest_Success() {
        // Given
        ExerciceSubmitRequest request = new ExerciceSubmitRequest("author", "testCode", "someData", "someOtherData");
        when(exerciceService.submitExercice(any())).thenReturn(new ObjectId("exampleObjectId"));

        // When
        Response response = exerciceController.submitExercice(request);

        // Then
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        JsonObject jsonResponse = (JsonObject) response.getEntity();
        assertEquals("exampleObjectId", jsonResponse.getString("id"));
        assertEquals("Exercice submitted successfully.", jsonResponse.getString("feedback"));
    }

    @Test
    void createExercice_ValidRequest_Success() {
        // Given
        ExerciceCreateRequest request = new ExerciceCreateRequest("author", "testCode", "language");
        when(exerciceService.createExercice(any())).thenReturn(Response.ok().build());

        // When
        Response response = exerciceController.createExercice(request);

        // Then
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    void getExerciceResultById_ValidId_Success() {
        // Given
        String id = "exampleId";
        when(exerciceService.getExerciceResultById(id)).thenReturn(Optional.of(new ExerciceResults()));

        // When
        Response response = exerciceController.getExerciceResultById(id);

        // Then
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    void getExerciceResultById_InvalidId_NotFound() {
        // Given
        String id = "invalidId";
        when(exerciceService.getExerciceResultById(id)).thenReturn(Optional.empty());

        // When / Then
        assertThrows(NotFoundException.class, () -> exerciceController.getExerciceResultById(id));
    }
}
