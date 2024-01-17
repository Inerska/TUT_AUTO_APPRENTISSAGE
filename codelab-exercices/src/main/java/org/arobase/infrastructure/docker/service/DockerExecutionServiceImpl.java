package org.arobase.infrastructure.docker.service;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.model.Frame;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.core.command.ExecStartResultCallback;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.unchecked.Unchecked;
import jakarta.enterprise.context.ApplicationScoped;
import org.arobase.domain.docker.service.DockerExecutionService;
import org.arobase.domain.docker.service.DockerQueueImageResolverFactory;
import org.arobase.domain.model.request.ExerciceSubmitRequest;
import org.arobase.infrastructure.persistence.service.ExerciceService;
import org.jboss.logging.Logger;

import java.io.Closeable;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@ApplicationScoped
public final class DockerExecutionServiceImpl implements DockerExecutionService {

    private final Logger logger;
    private final DockerQueueImageResolverFactory dockerQueueImageFactory;
    private final ExerciceService exerciceService;

    public DockerExecutionServiceImpl(DockerQueueImageResolverFactory dockerQueueImageFactory, Logger logger, ExerciceService exerciceService) {
        this.dockerQueueImageFactory = dockerQueueImageFactory;
        this.logger = logger;
        this.exerciceService = exerciceService;
    }

    @Override
    public Uni<String> executeCode(final ExerciceSubmitRequest exerciceSubmitRequest) {
        return Uni.createFrom().item(DefaultDockerClientConfig.createDefaultConfigBuilder().build())
                .onItem().transformToUni(config -> {
                    DockerClient dockerClient = DockerClientImpl.getInstance(config, new ApacheDockerHttpClient.Builder()
                            .dockerHost(URI.create("unix:///var/run/docker.sock"))
                            .sslConfig(config.getSSLConfig())
                            .build());

                    String dockerContainerImage = dockerQueueImageFactory.resolve(exerciceSubmitRequest.getLanguage())
                            .orElseThrow(() -> new IllegalArgumentException("No docker image found for language " + exerciceSubmitRequest.getLanguage()));

                    return Uni.createFrom().emitter(emitter -> {
                        try {
                            dockerClient.pullImageCmd(dockerContainerImage).start().awaitCompletion();
                            emitter.complete(null);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            emitter.fail(e);
                        }
                    }).onItem().transformToUni(ignored -> {
                        var createContainerResponse = dockerClient.createContainerCmd(dockerContainerImage)
                                .withTty(true)
                                .withStdinOpen(true)
                                .exec();

                        dockerClient.startContainerCmd(createContainerResponse.getId()).exec();

                        return Uni.combine().all().unis(
                                        executeCommandInContainer(dockerClient, createContainerResponse.getId(), "echo '" + exerciceSubmitRequest.getCode() + "' > /home/main.py"),
                                        executeCommandInContainer(dockerClient, createContainerResponse.getId(), "echo '" + exerciceService.getTestCodeByExerciceId(exerciceSubmitRequest.getExerciceId()) + "' > /home/test.py")
                                ).asTuple()
                                .onItem().transformToUni(ignoredTuple -> executeCommandInContainer(dockerClient, createContainerResponse.getId(), "cd /home && python3 -m unittest test.py"))
                                .onItem().transformToUni(testResults -> {
                                    dockerClient.stopContainerCmd(createContainerResponse.getId()).exec();
                                    dockerClient.removeContainerCmd(createContainerResponse.getId()).exec();
                                    return Uni.createFrom().item(testResults);
                                });
                    });
                });
    }

    private Uni<String> executeCommandInContainer(DockerClient dockerClient, String containerId, String command) {
        return Uni.createFrom().emitter(Unchecked.consumer(emitter -> {
            try {
                String execId = dockerClient.execCreateCmd(containerId)
                        .withAttachStdout(true)
                        .withAttachStderr(true)
                        .withCmd("sh", "-c", command)
                        .exec()
                        .getId();

                final var stringBuilder = new StringBuilder();
                CompletableFuture<Boolean> future = new CompletableFuture<>();
                dockerClient.execStartCmd(execId)
                        .exec(new ResultCallback<Frame>() {

                            @Override
                            public void onStart(Closeable closeable) {

                            }

                            @Override
                            public void onNext(Frame object) {
                                stringBuilder.append(new String(object.getPayload()));
                            }

                            @Override
                            public void onError(Throwable throwable) {
                                future.completeExceptionally(throwable);
                            }

                            @Override
                            public void onComplete() {
                                future.complete(true);
                            }

                            @Override
                            public void close() throws IOException {

                            }
                        });

                        future.get();

                emitter.complete(stringBuilder.toString());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                emitter.fail(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }));
    }
}