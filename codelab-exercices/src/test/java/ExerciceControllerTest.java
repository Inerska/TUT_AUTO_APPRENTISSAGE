import io.vertx.core.json.JsonObject;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import org.arobase.domain.model.request.ExerciceSubmitRequest;
import org.arobase.infrastructure.persistence.entity.ExerciceResults;
import org.arobase.infrastructure.persistence.service.BodyValidatorService;
import org.arobase.infrastructure.persistence.service.ExerciceService;
import org.arobase.web.controller.ExerciceController;
import org.bson.types.ObjectId;
import org.jboss.logging.Logger;

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

public class ExerciceControllerTest {

    @Mock
    private ExerciceService exerciceService;

    @Mock
    private BodyValidatorService bodyValidatorService;

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
        ExerciceSubmitRequest request = new ExerciceSubmitRequest("author", "testCode", "someData", "someOtherData");
        when(exerciceService.submitExercice(any())).thenReturn(new ObjectId("507f191e810c19729de860ea"));

        Response response = exerciceController.submitExercice(request);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        JsonObject jsonResponse = (JsonObject) response.getEntity();
        assertEquals("507f191e810c19729de860ea", jsonResponse.getString("id"));
        assertEquals("Exercice submitted successfully.", jsonResponse.getString("feedback"));
    }
    @Test
    void getExerciceResultById_ValidId_Success() {
        String id = "exampleId";
        when(exerciceService.getExerciceResultById(id)).thenReturn(Optional.of(new ExerciceResults()));

        Response response = exerciceController.getExerciceResultById(id);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    void getExerciceResultById_InvalidId_NotFound() {
        String id = "invalidId";
        when(exerciceService.getExerciceResultById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> exerciceController.getExerciceResultById(id));
    }
}
