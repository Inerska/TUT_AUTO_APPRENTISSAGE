import io.smallrye.mutiny.Uni;
import org.arobase.domain.docker.service.DockerExecutionService;
import org.arobase.domain.model.request.ExerciceSubmitRequest;
import org.arobase.infrastructure.messaging.processor.ExerciceMessagingRequestProcessor;
import org.arobase.infrastructure.persistence.service.ExerciceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.jboss.logging.Logger;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Classe de test pour la classe ExerciceMessagingRequestProcessor.
 */
class ExerciceMessagingRequestProcessorTest {

    @Mock
    private ExerciceService exerciceService;

    @Mock
    private DockerExecutionService dockerExecutionService;

    @Mock
    private Logger logger;

    private ExerciceMessagingRequestProcessor processor;

    /**
     * Méthode exécutée avant chaque test.
     * Initialise les mocks et l'instance du processeur à tester.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        processor = new ExerciceMessagingRequestProcessor(exerciceService, dockerExecutionService, logger);
    }

    /**
     * Teste le traitement d'une requête d'exercice avec succès.
     */
    @Test
    void process_ShouldProcessExerciceAndHandleSuccess() {
        ExerciceSubmitRequest request = new ExerciceSubmitRequest("code", "java", "exerciceId", "resultObjectId");
        when(dockerExecutionService.executeCode(request)).thenReturn(Uni.createFrom().item("exerciceResponse"));

        processor.process(request);

        verify(exerciceService).processExerciceResultById("resultObjectId");
        verify(exerciceService).updateExerciceResult("resultObjectId", "COMPLETED", "exerciceResponse");
        verify(logger).info("Exercice " + request + " processed.");
        verify(logger, never()).error(any(), any(Throwable.class)); // On s'assure qu'aucune erreur n'a été enregistrée
    }

}