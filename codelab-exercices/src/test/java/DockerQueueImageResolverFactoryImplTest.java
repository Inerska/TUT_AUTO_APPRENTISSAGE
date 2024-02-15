import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.arobase.infrastructure.docker.service.DockerQueueImageResolverFactoryImpl;

class DockerQueueImageResolverFactoryImplTest {
    @Test
    void resolve_WithJavaLanguage_ShouldReturnCorrectImage() {
        // Given
        DockerQueueImageResolverFactoryImpl factory = new DockerQueueImageResolverFactoryImpl();

        // When
        Optional<String> result = factory.resolve("java");

        // Then
        assertEquals("openjdk:11", result.get());
    }

    @Test
    void resolve_WithPythonLanguage_ShouldReturnCorrectImage() {
        // Given
        DockerQueueImageResolverFactoryImpl factory = new DockerQueueImageResolverFactoryImpl();

        // When
        Optional<String> result = factory.resolve("python");

        // Then
        assertEquals("python:latest", result.get());
    }

    @Test
    void resolve_WithUnknownLanguage_ShouldReturnEmptyOptional() {
        // Given
        DockerQueueImageResolverFactoryImpl factory = new DockerQueueImageResolverFactoryImpl();

        // When
        Optional<String> result = factory.resolve("unknown");

        // Then
        assertEquals(Optional.empty(), result);
    }
}
