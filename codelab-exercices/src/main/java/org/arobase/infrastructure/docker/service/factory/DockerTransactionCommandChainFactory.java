package org.arobase.infrastructure.docker.service.factory;

import io.smallrye.mutiny.tuples.Tuple2;
import jakarta.enterprise.context.ApplicationScoped;
import org.arobase.domain.docker.service.DockerTransactionCommandChain;
import org.arobase.infrastructure.docker.service.chain.PythonDockerTransactionCommandChain;
import org.arobase.infrastructure.docker.util.DockerCommandExecutor;
import org.arobase.infrastructure.persistence.service.ExerciceService;

import java.util.*;
import java.util.stream.Collectors;

@ApplicationScoped
public class DockerTransactionCommandChainFactory {

    private final ExerciceService exerciceService;
    private final DockerCommandExecutor commandExecutor;
    private final Map<Tuple2<String, String>, DockerTransactionCommandChain> commandChainMap;

    public DockerTransactionCommandChainFactory(ExerciceService exerciceService, DockerCommandExecutor commandExecutor) {
        this.exerciceService = exerciceService;
        this.commandExecutor = commandExecutor;
        this.commandChainMap = new HashMap<>();
        initializeCommandChains();
    }

    private void initializeCommandChains() {
        commandChainMap.put(Tuple2.of("python", "py"), new PythonDockerTransactionCommandChain(exerciceService, commandExecutor));
    }

    public DockerTransactionCommandChain getChain(final String identifier) {
        Optional<Tuple2<String, String>> key = commandChainMap.keySet().stream()
                .filter(k -> k.getItem1().equals(identifier) || k.getItem2().equals(identifier))
                .findFirst();

        if (key.isPresent()) {
            return commandChainMap.get(key.get());
        } else {
            throw new IllegalArgumentException("Unsupported language or abbreviation: " + identifier);
        }
    }

    public List<Tuple2<String, String>> getSupportedLanguages() {
        return commandChainMap.keySet().stream()
                .map(k -> Tuple2.of(k.getItem1(), k.getItem2()))
                .collect(Collectors.toList());
    }
}