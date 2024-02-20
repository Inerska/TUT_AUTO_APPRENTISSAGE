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
        // Préparation de l'objet à tester
        DockerQueueImageResolverFactoryImpl factory = new DockerQueueImageResolverFactoryImpl();

        // Appel de la méthode de résolution pour le langage Java
        Optional<String> result = factory.resolve("java");

        // Vérification du résultat
        assertEquals("openjdk:11", result.get());
    }

    /**
     * Teste la résolution de l'image Docker pour le langage Python.
     * Devrait retourner l'image Docker correcte pour Python.
     */
    @Test
    void resolve_WithPythonLanguage_ShouldReturnCorrectImage() {
        // Préparation de l'objet à tester
        DockerQueueImageResolverFactoryImpl factory = new DockerQueueImageResolverFactoryImpl();

        // Appel de la méthode de résolution pour le langage Python
        Optional<String> result = factory.resolve("python");

        // Vérification du résultat
        assertEquals("python:latest", result.get());
    }

    /**
     * Teste la résolution de l'image Docker pour un langage inconnu.
     * Devrait retourner un Optional vide car le langage est inconnu.
     */
    @Test
    void resolve_WithUnknownLanguage_ShouldReturnEmptyOptional() {
        // Préparation de l'objet à tester
        DockerQueueImageResolverFactoryImpl factory = new DockerQueueImageResolverFactoryImpl();

        // Appel de la méthode de résolution pour un langage inconnu
        Optional<String> result = factory.resolve("unknown");

        // Vérification du résultat
        assertEquals(Optional.empty(), result);
    }
}
