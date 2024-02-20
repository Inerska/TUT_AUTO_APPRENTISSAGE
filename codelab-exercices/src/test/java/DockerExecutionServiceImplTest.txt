import io.smallrye.mutiny.Uni;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;
import org.arobase.domain.docker.service.DockerExecutionContext;
import org.arobase.domain.docker.service.DockerExecutionService;
import org.arobase.domain.docker.service.DockerQueueImageResolverFactory;
import org.arobase.domain.model.request.ExerciceSubmitRequest;
import org.arobase.infrastructure.docker.service.factory.DockerTransactionCommandChainFactory;
import org.arobase.infrastructure.persistence.service.ExerciceService;
import org.jboss.logging.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.arobase.domain.docker.service.DockerQueueImageResolverFactory;
import org.arobase.infrastructure.persistence.service.ExerciceService;
import org.arobase.infrastructure.docker.service.factory.DockerTransactionCommandChainFactory;
import org.arobase.infrastructure.docker.service.DockerExecutionServiceImpl;
import org.arobase.domain.model.request.ExerciceSubmitRequest;
import org.arobase.domain.docker.service.DockerExecutionService;
import org.arobase.domain.docker.service.DockerTransactionCommandChain;

import java.util.Optional;

import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;

class DockerExecutionServiceImplTest {

    private DockerQueueImageResolverFactory dockerQueueImageFactory;
    private Logger logger;
    private ExerciceService exerciceService;
    private DockerTransactionCommandChainFactory dockerTransactionCommandChainFactory;
    private DockerExecutionServiceImpl dockerExecutionService;

    @Mock // Marquer les champs pour la création de mocks
    private DockerQueueImageResolverFactory dockerQueueImageFactoryMock;

    @Mock
    private Logger loggerMock;

    @Mock
    private ExerciceService exerciceServiceMock;

    @Mock
    private DockerTransactionCommandChainFactory dockerTransactionCommandChainFactoryMock;

    @BeforeEach
    void setUp() {
        // Initialiser les mocks correctement
        MockitoAnnotations.openMocks(this);

        dockerExecutionService = new DockerExecutionServiceImpl(
                dockerQueueImageFactoryMock,
                loggerMock,
                exerciceServiceMock,
                dockerTransactionCommandChainFactoryMock
        );
    }

    @Test
    void executeCode_Success() {
        // Given
        ExerciceSubmitRequest request = new ExerciceSubmitRequest("id", "code", "language", "resultId");

        DockerTransactionCommandChain commandChain = mock(DockerTransactionCommandChain.class);
        when(dockerTransactionCommandChainFactoryMock.getChain(anyString())).thenReturn(commandChain);

        // When
        dockerExecutionService.executeCode(request);

        // Then
        verify(exerciceServiceMock).updateExerciceResult(eq("resultId"), eq("ERROR"), anyString());
        verify(loggerMock).error(anyString(), any(Throwable.class));
    }

    @Test
    void executeCode_Exception() {
        // Given
        ExerciceSubmitRequest request = new ExerciceSubmitRequest("id", "code", "language", "resultId");

        when(dockerQueueImageFactoryMock.resolve(anyString())).thenReturn(Optional.of("someImage"));
        when(dockerTransactionCommandChainFactoryMock.getChain(anyString())).thenThrow(new RuntimeException("Test Exception"));

        // When
        dockerExecutionService.executeCode(request);

        // Then
        verify(exerciceServiceMock).updateExerciceResult(eq("resultId"), eq("ERROR"), anyString());
        verify(loggerMock).error(anyString(), any(Throwable.class));
    }
}
