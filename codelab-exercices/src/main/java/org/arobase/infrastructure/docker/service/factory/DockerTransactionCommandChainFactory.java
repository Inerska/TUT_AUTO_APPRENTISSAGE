package org.arobase.infrastructure.docker.service.factory;

import jakarta.enterprise.context.ApplicationScoped;
import org.arobase.domain.docker.service.DockerTransactionCommandChain;
import org.arobase.infrastructure.docker.service.chain.PythonDockerTransactionCommandChain;
import org.arobase.infrastructure.docker.util.DockerCommandExecutor;
import org.arobase.infrastructure.persistence.service.ExerciceService;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class DockerTransactionCommandChainFactory {

    private final ExerciceService exerciceService;
    private final DockerCommandExecutor commandExecutor;
    private final Map<String, DockerTransactionCommandChain> commandChainMap;

    public DockerTransactionCommandChainFactory(ExerciceService exerciceService, DockerCommandExecutor commandExecutor) {
        this.exerciceService = exerciceService;
        this.commandExecutor = commandExecutor;
        this.commandChainMap = new HashMap<>();
        initializeCommandChains();
    }

    private void initializeCommandChains() {
        commandChainMap.put("python", new PythonDockerTransactionCommandChain(exerciceService, commandExecutor));
    }

    public DockerTransactionCommandChain getChain(final String language) {
        if (!commandChainMap.containsKey(language)) {
            throw new IllegalArgumentException("Unsupported language: " + language);
        }
        return commandChainMap.get(language);
    }

    public List<String> getSupportedLanguages() {
        return Collections.unmodifiableList(List.copyOf(commandChainMap.keySet()));
    }
}
