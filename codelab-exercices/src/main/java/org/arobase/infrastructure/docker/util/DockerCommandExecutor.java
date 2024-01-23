package org.arobase.infrastructure.docker.util;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.model.Frame;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.unchecked.Unchecked;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@ApplicationScoped
public class DockerCommandExecutor {

    public Uni<String> executeCommandInContainer(DockerClient dockerClient, String containerId, String command) {
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
            } catch (InterruptedException | ExecutionException e) {
                emitter.fail(e);
            }
        }));
    }
}
