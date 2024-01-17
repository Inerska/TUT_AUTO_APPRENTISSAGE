package org.arobase.infrastructure.docker.service;

import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.jaxrs.JerseyDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;
import jakarta.enterprise.context.ApplicationScoped;
import org.arobase.domain.model.ExerciceRequest;
import org.arobase.domain.docker.service.DockerExecutionService;
import org.arobase.domain.docker.service.DockerQueueImageResolverFactory;
import org.jboss.logging.Logger;

import java.io.IOException;
import java.net.URI;

@ApplicationScoped
public final class DockerExecutionServiceImpl implements DockerExecutionService {

    private final Logger logger;
    private final DockerQueueImageResolverFactory dockerQueueImageFactory;

    public DockerExecutionServiceImpl(DockerQueueImageResolverFactory dockerQueueImageFactory, Logger logger) {
        this.dockerQueueImageFactory = dockerQueueImageFactory;
        this.logger = logger;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String executeCode(final ExerciceRequest exerciceRequest) {

        final var dockerClientConfig = DefaultDockerClientConfig.createDefaultConfigBuilder().build();
        final var dockerClientHttpClient = new ApacheDockerHttpClient.Builder()
                .dockerHost(URI.create("unix:///var/run/docker.sock"))
                .sslConfig(dockerClientConfig.getSSLConfig())
                .build();

        logger.info("Executing exercice " + exerciceRequest + "...");
        try (final var dockerClient = DockerClientImpl.getInstance(dockerClientConfig, dockerClientHttpClient)) {
            logger.info("Docker client created.");
            final var dockerContainerImage = dockerQueueImageFactory.resolve(exerciceRequest.getLanguage())
                    .orElseThrow(() -> new IllegalArgumentException("No docker image found for language " + exerciceRequest.getLanguage()));
            logger.info("Docker container image resolved: " + dockerContainerImage);
            final var createContainerResponse = dockerClient.createContainerCmd(dockerContainerImage)
                    .exec();
            logger.info("Docker container created: " + createContainerResponse.getId());

            dockerClient.startContainerCmd(createContainerResponse.getId()).exec();

            //TODO: cp code

            //dockerClient.stopContainerCmd(createContainerResponse.getId()).exec();
            //dockerClient.removeContainerCmd(createContainerResponse.getId()).exec();

            return "TODO: Get results";

        } catch (final IOException e) {
            e.printStackTrace();
            return "Error while executing " + e.getMessage();
        }
    }
}
