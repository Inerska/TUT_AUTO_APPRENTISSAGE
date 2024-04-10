import org.arobase.infrastructure.docker.util.DockerCommandExecutor;
import com.github.dockerjava.api.DockerClient;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.helpers.test.UniAssertSubscriber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;

/**
 * Classe de test pour la classe DockerCommandExecutor.
 * Cette classe vise à tester le bon fonctionnement de la méthode executeCommandInContainer de la classe DockerCommandExecutor.
 */
public class DockerCommandExecutorTest {
    private DockerClient dockerClientMock;
    private DockerCommandExecutor dockerCommandExecutor;

    /**
     * Méthode exécutée avant chaque test.
     * Initialise les mocks et les instances nécessaires.
     */
    @BeforeEach
    public void setUp() {
        dockerClientMock = mock(DockerClient.class);
        dockerCommandExecutor = new DockerCommandExecutor();
    }

    /**
     * Teste la méthode executeCommandInContainer avec une exception.
     * Cette méthode vérifie si la méthode executeCommandInContainer lance une exception
     * lorsqu'elle est exécutée avec un conteneur et une commande invalides.
     */
    @Test
    public void testExecuteCommandInContainer_WithException() {
        String containerId = "testContainerId";
        String command = "echo 'Hello, World!'";

        Uni<String> resultUni = dockerCommandExecutor.executeCommandInContainer(dockerClientMock, containerId, command);
        UniAssertSubscriber<String> subscriber = resultUni.subscribe().withSubscriber(UniAssertSubscriber.create());

        subscriber.assertFailed();
    }

}