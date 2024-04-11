package org.arobase.domain.docker.service;

import com.github.dockerjava.api.DockerClient;

/**
 * Defines a docker execution context.
 */
public class DockerExecutionContext {

    /**
     * The docker client instance.
     */
    private final DockerClient dockerClient;

    /**
     * The container id.
     */
    private final String containerId;

    public DockerExecutionContext(DockerClient dockerClient, String containerId) {
        this.dockerClient = dockerClient;
        this.containerId = containerId;
    }

    public DockerClient getDockerClient() {
        return dockerClient;
    }

    public String getContainerId() {
        return containerId;
    }
}