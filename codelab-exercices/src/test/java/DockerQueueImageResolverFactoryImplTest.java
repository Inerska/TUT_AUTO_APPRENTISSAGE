import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.arobase.infrastructure.docker.service.DockerQueueImageResolverFactoryImpl;

/**
 * Classe de test pour la classe DockerQueueImageResolverFactoryImpl.
 */
class DockerQueueImageResolverFactoryImplTest {

    /**
     * Teste la résolution de l'image Docker pour le langage Java.
     * Devrait retourner l'image Docker correcte pour Java.
     */
    @Test
    void resolve_WithJavaLanguage_ShouldReturnCorrectImage() {
        DockerQueueImageResolverFactoryImpl factory = new DockerQueueImageResolverFactoryImpl();

        Optional<String> result = factory.resolve("java");

        assertEquals("openjdk:11", result.get());
    }

    /**
     * Teste la résolution de l'image Docker pour le langage Python.
     * Devrait retourner l'image Docker correcte pour Python.
     */
    @Test
    void resolve_WithPythonLanguage_ShouldReturnCorrectImage() {
        DockerQueueImageResolverFactoryImpl factory = new DockerQueueImageResolverFactoryImpl();

        Optional<String> result = factory.resolve("python");

        assertEquals("python:3.9", result.get());
    }

    /**
     * Teste la résolution de l'image Docker pour un langage inconnu.
     * Devrait retourner un Optional vide car le langage est inconnu.
     */
    @Test
    void resolve_WithUnknownLanguage_ShouldReturnEmptyOptional() {
        DockerQueueImageResolverFactoryImpl factory = new DockerQueueImageResolverFactoryImpl();

        Optional<String> result = factory.resolve("unknown");

        assertEquals(Optional.empty(), result);
    }
}