import com.github.dockerjava.api.DockerClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.arobase.domain.docker.service.DockerExecutionContext;

/**
 * Classe de test pour la classe DockerExecutionContext.
 */
public class DockerExecutionContextTest {
    private DockerClient dockerClientMock;
    private final String containerId = "testContainerId";
    private DockerExecutionContext dockerExecutionContext;

    /**
     * Méthode exécutée avant chaque test.
     * Initialise les mocks et les instances nécessaires.
     */
    @BeforeEach
    public void setUp() {
        dockerClientMock = mock(DockerClient.class);
        dockerExecutionContext = new DockerExecutionContext(dockerClientMock, containerId);
    }

    /**
     * Teste la méthode getDockerClient.
     * Vérifie si le DockerClient retourné est celui qui a été passé lors de la création de l'instance.
     */
    @Test
    public void testGetDockerClient() {
        assertEquals(dockerClientMock, dockerExecutionContext.getDockerClient());
    }

    /**
     * Teste la méthode getContainerId.
     * Vérifie si l'identifiant du conteneur retourné est celui qui a été passé lors de la création de l'instance.
     */
    @Test
    public void testGetContainerId() {
        assertEquals(containerId, dockerExecutionContext.getContainerId());
    }
}