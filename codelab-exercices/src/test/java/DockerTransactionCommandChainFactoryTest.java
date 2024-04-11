import org.arobase.domain.docker.service.DockerTransactionCommandChain;
import org.arobase.infrastructure.docker.service.chain.PythonDockerTransactionCommandChain;
import org.arobase.infrastructure.docker.util.DockerCommandExecutor;
import org.arobase.infrastructure.persistence.service.ExerciceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.arobase.infrastructure.docker.service.factory.DockerTransactionCommandChainFactory;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Classe de test pour la classe DockerTransactionCommandChainFactory.
 */
class DockerTransactionCommandChainFactoryTest {

    private DockerTransactionCommandChainFactory factory;

    /**
     * Méthode exécutée avant chaque test.
     * Initialise les dépendances nécessaires.
     */
    @BeforeEach
    void setUp() {
        ExerciceService exerciceService = mock(ExerciceService.class);
        DockerCommandExecutor commandExecutor = mock(DockerCommandExecutor.class);
        factory = new DockerTransactionCommandChainFactory(exerciceService, commandExecutor);
    }

    /**
     * Teste la récupération de la chaîne de commandes Docker pour le langage Python.
     * Doit retourner une instance de PythonDockerTransactionCommandChain.
     */
    @Test
    void getChain_withPythonLanguage_shouldReturnPythonDockerTransactionCommandChain() {
        // Préparation des données nécessaires pour le test
        String language = "python";

        // Appel de la méthode à tester
        DockerTransactionCommandChain chain = factory.getChain(language);

        // Vérification de la classe retournée
        assertInstanceOf(PythonDockerTransactionCommandChain.class, chain);
    }

    /**
     * Teste la récupération de la chaîne de commandes Docker pour un langage non pris en charge.
     * Doit lancer une IllegalArgumentException.
     */
    @Test
    void getChain_withUnsupportedLanguage_shouldThrowIllegalArgumentException() {
        // Préparation des données nécessaires pour le test
        String language = "java";

        // Vérification du lancement de l'exception
        assertThrows(IllegalArgumentException.class, () -> factory.getChain(language));
    }

    /**
     * Teste la récupération de la chaîne de commandes Docker pour un langage vide.
     * Doit lancer une IllegalArgumentException.
     */
    @Test
    void getChain_withEmptyLanguage_shouldThrowIllegalArgumentException() {
        // Vérification du lancement de l'exception
        assertThrows(IllegalArgumentException.class, () -> factory.getChain(""));
    }
}