import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.core.Response;
import org.arobase.infrastructure.persistence.service.ExerciceService;
import org.arobase.web.controller.ListLanguageExercicesController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class ListLanguageExercicesControllerTest {

    @Mock
    private ExerciceService exerciceService;

    @InjectMocks
    private ListLanguageExercicesController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listExercises_ValidLanguage_ReturnsExercises() {
        String language = "Java";
        when(exerciceService.listExercicesByLanguage(language)).thenReturn(Uni.createFrom().item(Collections.emptyList()));

        Uni<Response> response = controller.listExercises(language);

        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.await().indefinitely().getStatus());
        verify(exerciceService, times(1)).listExercicesByLanguage(language);
    }

    @Test
    void listExercises_NullLanguage_ReturnsBadRequest() {
        Uni<Response> response = controller.listExercises(null);

        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.await().indefinitely().getStatus());
        verifyNoInteractions(exerciceService);
    }

    @Test
    void listExercises_BlankLanguage_ReturnsBadRequest() {
        Uni<Response> response = controller.listExercises("   ");

        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.await().indefinitely().getStatus());
        verifyNoInteractions(exerciceService);
    }

    @Test
    void listExercises_ServiceFails_ReturnsInternalServerError() {
        String language = "Python";
        when(exerciceService.listExercicesByLanguage(anyString())).thenReturn(Uni.createFrom().failure(new RuntimeException()));

        Uni<Response> response = controller.listExercises(language);

        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.await().indefinitely().getStatus());
        verify(exerciceService, times(1)).listExercicesByLanguage(language);
    }
}
