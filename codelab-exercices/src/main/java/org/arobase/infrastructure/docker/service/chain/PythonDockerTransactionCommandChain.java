package org.arobase.infrastructure.docker.service.chain;

import io.smallrye.mutiny.Uni;
import org.arobase.domain.docker.service.DockerExecutionContext;
import org.arobase.domain.docker.service.DockerTransactionCommandChain;
import org.arobase.domain.model.request.ExerciceSubmitRequest;
import org.arobase.infrastructure.docker.util.DockerCommandExecutor;
import org.arobase.infrastructure.persistence.service.ExerciceService;

/**
 * Defines a docker transaction command chain for python.
 *
 * @see DockerExecutionContext
 * @see DockerTransactionCommandChain
 */
public class PythonDockerTransactionCommandChain implements DockerTransactionCommandChain {

    private final ExerciceService exerciceService;
    private final DockerCommandExecutor commandExecutor;

    public PythonDockerTransactionCommandChain(ExerciceService exerciceService, DockerCommandExecutor commandExecutor) {
        this.exerciceService = exerciceService;
        this.commandExecutor = commandExecutor;
    }

    @Override
    public Uni<String> execute(final DockerExecutionContext context, final ExerciceSubmitRequest exerciceSubmitRequest) {
        String code = exerciceSubmitRequest.getCode();
        String testCode = exerciceService.getTestCodeByExerciceId(exerciceSubmitRequest.getExerciceId());
        String containerId = context.getContainerId();

        return Uni.combine().all().unis(
                commandExecutor.executeCommandInContainer(context.getDockerClient(), containerId, "echo '" + code + "' > /home/main.py"),
                commandExecutor.executeCommandInContainer(context.getDockerClient(), containerId, "echo '" + testCode + "' > /home/test.py"),
                commandExecutor.executeCommandInContainer(context.getDockerClient(), containerId, "cd /home && python3 -m unittest test.py")
        ).asTuple().onItem().transformToUni(ignoredTuple ->
                Uni.createFrom().item(ignoredTuple.getItem3())
        );
    }
}
