import com.github.dockerjava.api.DockerClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.arobase.domain.docker.service.DockerExecutionContext;

public class DockerExecutionContextTest {

    private DockerClient dockerClientMock;
    private String containerId = "testContainerId";
    private DockerExecutionContext dockerExecutionContext;

    @BeforeEach
    public void setUp() {
        dockerClientMock = mock(DockerClient.class);
        dockerExecutionContext = new DockerExecutionContext(dockerClientMock, containerId);
    }

    @Test
    public void testGetDockerClient() {
        assertEquals(dockerClientMock, dockerExecutionContext.getDockerClient());
    }

    @Test
    public void testGetContainerId() {
        assertEquals(containerId, dockerExecutionContext.getContainerId());
    }
}
