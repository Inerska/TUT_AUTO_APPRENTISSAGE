package org.arobase.infrastructure.docker.service;

import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.arobase.domain.docker.service.DockerExecutionContext;
import org.arobase.domain.docker.service.DockerExecutionService;
import org.arobase.domain.docker.service.DockerQueueImageResolverFactory;
import org.arobase.domain.model.request.ExerciceSubmitRequest;
import org.arobase.infrastructure.docker.service.factory.DockerTransactionCommandChainFactory;
import org.arobase.infrastructure.persistence.service.ExerciceService;
import org.jboss.logging.Logger;

import java.net.URI;

@ApplicationScoped
public final class DockerExecutionServiceImpl implements DockerExecutionService {

    private final Logger logger;
    private final DockerQueueImageResolverFactory dockerQueueImageFactory;
    private final ExerciceService exerciceService;

    private final DockerTransactionCommandChainFactory dockerTransactionCommandChainFactory;

    public DockerExecutionServiceImpl(
            DockerQueueImageResolverFactory dockerQueueImageFactory,
            Logger logger,
            ExerciceService exerciceService,
            DockerTransactionCommandChainFactory dockerTransactionCommandChainFactory) {
        this.dockerQueueImageFactory = dockerQueueImageFactory;
        this.logger = logger;
        this.exerciceService = exerciceService;
        this.dockerTransactionCommandChainFactory = dockerTransactionCommandChainFactory;
    }

    @Override
    public Uni<String> executeCode(final ExerciceSubmitRequest exerciceSubmitRequest) {
        return Uni.createFrom().item(DefaultDockerClientConfig.createDefaultConfigBuilder().build())
                .onItem().transformToUni(config -> {
                    final var dockerClient = DockerClientImpl.getInstance(config, new ApacheDockerHttpClient.Builder()
                            .dockerHost(URI.create("unix:///var/run/docker.sock"))
                            .sslConfig(config.getSSLConfig())
                            .build());

                    final var dockerContainerImage = dockerQueueImageFactory.resolve(exerciceSubmitRequest.getLanguage())
                            .orElseThrow(() -> new IllegalArgumentException("No docker image found for language " + exerciceSubmitRequest.getLanguage()));

                    var createContainerResponse = dockerClient.createContainerCmd(dockerContainerImage)
                            .withTty(true)
                            .withStdinOpen(true)
                            .exec();

                    dockerClient.startContainerCmd(createContainerResponse.getId()).exec();

                    final var context = new DockerExecutionContext(dockerClient, createContainerResponse.getId());
                    final var commandChain = dockerTransactionCommandChainFactory.getChain(exerciceSubmitRequest.getLanguage());

                    try {
                        return commandChain.execute(context, exerciceSubmitRequest)
                                .onFailure().invoke(failure -> {
                                    logger.error("Error executing exercice " + exerciceSubmitRequest, failure);
                                    exerciceService.updateExerciceResult(exerciceSubmitRequest.getExerciceResultObjectId(), "ERROR", failure.getMessage());
                                });
                    } catch (Exception e) {
                        logger.error("Error executing exercice " + exerciceSubmitRequest, e);
                        exerciceService.updateExerciceResult(exerciceSubmitRequest.getExerciceResultObjectId(), "ERROR", e.getMessage());
                        return Uni.createFrom().item(e.getMessage());
                    }
                });
    }
}