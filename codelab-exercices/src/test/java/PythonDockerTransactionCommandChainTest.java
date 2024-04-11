import io.smallrye.mutiny.Uni;
import org.arobase.domain.docker.service.DockerExecutionContext;
import org.arobase.domain.model.request.ExerciceSubmitRequest;
import org.arobase.infrastructure.docker.service.chain.PythonDockerTransactionCommandChain;
import org.arobase.infrastructure.docker.util.DockerCommandExecutor;
import org.arobase.infrastructure.persistence.service.ExerciceService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Classe de test pour la classe PythonDockerTransactionCommandChain.
 */
public class PythonDockerTransactionCommandChainTest {

    /**
     * Teste la méthode execute de PythonDockerTransactionCommandChain.
     */
    @Test
    public void testExecute() {
        ExerciceService exerciceService = mock(ExerciceService.class);
        DockerCommandExecutor commandExecutor = mock(DockerCommandExecutor.class);
        DockerExecutionContext context = mock(DockerExecutionContext.class);
        ExerciceSubmitRequest request = new ExerciceSubmitRequest("code", "Python", "exerciceId", "resultObjectId");

        when(exerciceService.getTestCodeByExerciceId(anyString())).thenReturn("testCode");
        when(context.getContainerId()).thenReturn("containerId");
        when(commandExecutor.executeCommandInContainer(any(), any(), anyString())).thenReturn(Uni.createFrom().item("executionResult"));

        PythonDockerTransactionCommandChain commandChain = new PythonDockerTransactionCommandChain(exerciceService, commandExecutor);

        Uni<String> result = commandChain.execute(context, request);

        assertEquals("executionResult", result.await().indefinitely());

        Mockito.verify(exerciceService).getTestCodeByExerciceId("exerciceId");
        Mockito.verify(context).getContainerId();
        Mockito.verify(commandExecutor).executeCommandInContainer(any(), any(), eq("echo 'code' > /home/main.py"));
        Mockito.verify(commandExecutor).executeCommandInContainer(any(), any(), eq("echo 'testCode' > /home/test.py"));
        Mockito.verify(commandExecutor).executeCommandInContainer(any(), any(), eq("cd /home && python3 -m unittest test.py"));
    }
}