import org.arobase.domain.docker.service.DockerTransactionCommandChain;
import org.arobase.infrastructure.docker.service.chain.PythonDockerTransactionCommandChain;
import org.arobase.infrastructure.docker.util.DockerCommandExecutor;
import org.arobase.infrastructure.persistence.service.ExerciceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.arobase.infrastructure.persistence.service.ExerciceService;
import org.arobase.infrastructure.docker.util.DockerCommandExecutor;
import org.arobase.infrastructure.docker.service.factory.DockerTransactionCommandChainFactory;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DockerTransactionCommandChainFactoryTest {

    private ExerciceService exerciceService;
    private DockerCommandExecutor commandExecutor;
    private DockerTransactionCommandChainFactory factory;

    @BeforeEach
    void setUp() {
        exerciceService = mock(ExerciceService.class);
        commandExecutor = mock(DockerCommandExecutor.class);
        factory = new DockerTransactionCommandChainFactory(exerciceService, commandExecutor);
    }

    @Test
    void getChain_withPythonLanguage_shouldReturnPythonDockerTransactionCommandChain() {
        // Given
        String language = "python";

        // When
        DockerTransactionCommandChain chain = factory.getChain(language);

        // Then
        assertTrue(chain instanceof PythonDockerTransactionCommandChain);
    }

    @Test
    void getChain_withUnsupportedLanguage_shouldThrowIllegalArgumentException() {
        // Given
        String language = "java";

        // When / Then
        assertThrows(IllegalArgumentException.class, () -> factory.getChain(language));
    }

    @Test
    void getChain_withEmptyLanguage_shouldThrowIllegalArgumentException() {
        // When / Then
        assertThrows(IllegalArgumentException.class, () -> factory.getChain(""));
    }
}
