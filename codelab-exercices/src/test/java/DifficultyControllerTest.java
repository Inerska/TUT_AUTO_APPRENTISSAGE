import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.core.Response;
import org.arobase.domain.model.request.DifficultyCreateRequest;
import org.arobase.infrastructure.persistence.entity.Difficulty;
import org.arobase.infrastructure.persistence.service.BodyValidatorService;
import org.arobase.infrastructure.persistence.service.DifficultyService;
import org.arobase.web.controller.DifficultyController;
import org.jboss.logging.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class DifficultyControllerTest {

    @Mock
    private BodyValidatorService bodyValidatorService;

    @Mock
    private DifficultyService difficultyService;

    @Mock
    private Logger logger;

    @InjectMocks
    private DifficultyController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller.bodyValidatorService = bodyValidatorService;
    }

    @Test
    void listDifficulties_ReturnsAllDifficulties() {
        when(difficultyService.findAllDifficulties()).thenReturn(Collections.emptyList());

        Uni<Response> response = controller.listDifficulties();

        assertEquals(Response.Status.OK.getStatusCode(), response.await().indefinitely().getStatus());
        verify(logger).info("GET /api/v1/difficulties called.");
        verify(difficultyService).findAllDifficulties();
    }

    @Test
    void getDifficultyById_ReturnsDifficultyById() {
        String id = "1";
        when(difficultyService.findDifficultyById(id)).thenReturn(mock(Difficulty.class));

        Response response = controller.getDifficultyById(id);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        verify(logger).info("GET /api/v1/difficulties/" + id + " called.");
        verify(difficultyService).findDifficultyById(id);
    }

    @Test
    void createDifficulty_CreatesDifficulty() {
        DifficultyCreateRequest request = mock(DifficultyCreateRequest.class);
        when(difficultyService.createDifficulty(any(DifficultyCreateRequest.class))).thenReturn(mock(Difficulty.class));

        Response response = controller.createDifficulty(request);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        verify(logger).info("POST /api/v1/difficulties for difficulty" + request.name() + " called.");
        verify(bodyValidatorService).validateBody(request);
        verify(difficultyService).createDifficulty(request);
    }

    @Test
    void updateDifficulty_UpdatesDifficulty() {
        String id = "1";
        DifficultyCreateRequest request = mock(DifficultyCreateRequest.class);
        when(difficultyService.updateDifficulty(anyString(), any(DifficultyCreateRequest.class))).thenReturn(mock(Difficulty.class));

        Response response = controller.updateDifficulty(id, request);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        verify(logger).info("PATCH /api/v1/difficulties for difficulty" + request.name() + " called.");
        verify(bodyValidatorService).validateBody(request);
        verify(difficultyService).updateDifficulty(id, request);
    }

    @Test
    void deleteDifficulty_DeletesDifficulty() {
        String id = "1";

        Response response = controller.deleteDifficulty(id);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        verify(logger).info("DELETE /api/v1/difficulties/" + id + " called.");
        verify(difficultyService).deleteDifficulty(id);
    }
}
