package org.arobase.infrastructure.docker.service.factory;

import jakarta.enterprise.context.ApplicationScoped;
import org.arobase.domain.docker.service.DockerTransactionCommandChain;
import org.arobase.infrastructure.docker.service.chain.PythonDockerTransactionCommandChain;
import org.arobase.infrastructure.docker.util.DockerCommandExecutor;
import org.arobase.infrastructure.persistence.service.ExerciceService;

@ApplicationScoped
public class DockerTransactionCommandChainFactory {

    private final ExerciceService exerciceService;

    private final DockerCommandExecutor commandExecutor;

    public DockerTransactionCommandChainFactory(ExerciceService exerciceService, DockerCommandExecutor commandExecutor) {
        this.exerciceService = exerciceService;
        this.commandExecutor = commandExecutor;
    }


    public DockerTransactionCommandChain getChain(final String language) {
        return switch (language) {
            case "python" -> new PythonDockerTransactionCommandChain(exerciceService, commandExecutor);
            default -> throw new IllegalArgumentException("Unsupported language: " + language);
        };
    }
}
