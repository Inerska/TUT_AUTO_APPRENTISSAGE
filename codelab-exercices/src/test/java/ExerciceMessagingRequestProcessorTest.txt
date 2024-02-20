package org.arobase.infrastructure.messaging.processor;

import io.smallrye.mutiny.Uni;
import org.arobase.domain.docker.service.DockerExecutionService;
import org.arobase.domain.model.request.ExerciceSubmitRequest;
import org.arobase.infrastructure.persistence.service.ExerciceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.jboss.logging.Logger;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ExerciceMessagingRequestProcessorTest {

    @Mock
    private ExerciceService exerciceService;

    @Mock
    private DockerExecutionService dockerExecutionService;

    @Mock
    private Logger logger;

    private ExerciceMessagingRequestProcessor processor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        processor = new ExerciceMessagingRequestProcessor(exerciceService, dockerExecutionService, logger);
    }

    @Test
    void process_ShouldProcessExerciceAndHandleSuccess() {
        // Given
        ExerciceSubmitRequest request = new ExerciceSubmitRequest("code", "java", "exerciceId", "resultObjectId");
        when(dockerExecutionService.executeCode(request)).thenReturn(Uni.createFrom().item("exerciceResponse"));

        // When
        processor.process(request);

        // Then
        verify(exerciceService).processExerciceResultById("resultObjectId");
        verify(exerciceService).updateExerciceResult("resultObjectId", "COMPLETED", "exerciceResponse");
        verify(logger).info("Exercice " + request + " processed.");
        verify(logger, never()).error(any(), any(Throwable.class)); // Correction ici
    }

}
