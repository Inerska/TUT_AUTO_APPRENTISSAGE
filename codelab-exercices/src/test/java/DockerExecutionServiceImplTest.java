import io.smallrye.mutiny.Uni;
import org.arobase.domain.docker.service.DockerExecutionService;
import org.arobase.domain.model.request.ExerciceSubmitRequest;
import org.arobase.infrastructure.docker.service.DockerExecutionServiceImpl;
import org.arobase.infrastructure.persistence.service.ExerciceService;
import org.jboss.logging.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.arobase.domain.docker.service.DockerQueueImageResolverFactory;
import org.arobase.infrastructure.docker.service.factory.DockerTransactionCommandChainFactory;
import org.arobase.domain.docker.service.DockerTransactionCommandChain;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Classe de test pour la classe DockerExecutionServiceImpl.
 */
class DockerExecutionServiceImplTest {

    @Mock
    private DockerQueueImageResolverFactory dockerQueueImageFactoryMock;

    @Mock
    private Logger loggerMock;

    @Mock
    private ExerciceService exerciceServiceMock;

    @Mock
    private DockerTransactionCommandChainFactory dockerTransactionCommandChainFactoryMock;

    @Mock
    private DockerTransactionCommandChain dockerTransactionCommandChainMock;

    private DockerExecutionService dockerExecutionService;

    /**
     * Méthode exécutée avant chaque test.
     * Initialise les mocks et les instances nécessaires.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        dockerExecutionService = new DockerExecutionServiceImpl(
                dockerQueueImageFactoryMock,
                loggerMock,
                exerciceServiceMock,
                dockerTransactionCommandChainFactoryMock
        );
    }

    /**
     * Teste l'exécution du code avec succès.
     */
    @Test
    void executeCode_Success() throws Exception {
        // Préparation des données nécessaires pour le test
        ExerciceSubmitRequest request = new ExerciceSubmitRequest("id", "code", "java", "resultId");
        when(dockerQueueImageFactoryMock.resolve("java")).thenReturn(Optional.of("openjdk:11"));
        when(dockerTransactionCommandChainFactoryMock.getChain("java")).thenReturn(dockerTransactionCommandChainMock);
        when(dockerTransactionCommandChainMock.execute(any(), any())).thenReturn(Uni.createFrom().item("Success"));

        // Exécution de la méthode à tester
        dockerExecutionService.executeCode(request).await().indefinitely();

        // Vérification des résultats ou du comportement attendu
        verify(exerciceServiceMock, never()).updateExerciceResult(anyString(), anyString(), anyString());
        verify(loggerMock, never()).error(anyString(), any(Throwable.class));
    }

    /**
     * Teste l'exécution du code lorsque l'image Docker n'est pas trouvée.
     */
    @Test
    void executeCode_ImageNotFound() {
        // Préparation des données nécessaires pour le test
        ExerciceSubmitRequest request = new ExerciceSubmitRequest("id", "code", "unknown", "resultId");
        when(dockerQueueImageFactoryMock.resolve("unknown")).thenReturn(Optional.empty());

        // Exécution de la méthode à tester
        dockerExecutionService.executeCode(request).await().indefinitely();

        // Vérification des résultats ou du comportement attendu
        verify(exerciceServiceMock).updateExerciceResult("resultId", "ERROR", anyString());
        verify(loggerMock).error(anyString(), any(Throwable.class));
    }

    /**
     * Teste l'exécution du code lorsqu'une exception se produit dans la chaîne de commandes Docker.
     */
    @Test
    void executeCode_ExceptionInChain() throws Exception {
        // Préparation des données nécessaires pour le test
        ExerciceSubmitRequest request = new ExerciceSubmitRequest("id", "code", "language", "resultId");
        when(dockerQueueImageFactoryMock.resolve(anyString())).thenReturn(Optional.of("someImage"));
        when(dockerTransactionCommandChainFactoryMock.getChain(anyString())).thenReturn(dockerTransactionCommandChainMock);
        when(dockerTransactionCommandChainMock.execute(any(), any())).thenReturn(Uni.createFrom().failure(new RuntimeException("Test Exception")));

        // Exécution de la méthode à tester
        dockerExecutionService.executeCode(request).subscribe().with(
                result -> {
                    assert false;
                },
                failure -> {
                    verify(exerciceServiceMock).updateExerciceResult("resultId", "ERROR", failure.getMessage());
                    verify(loggerMock).error(anyString(), any(Throwable.class));
                }
        );
    }
}
