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
        String language = "python";

        DockerTransactionCommandChain chain = factory.getChain(language);

        assertInstanceOf(PythonDockerTransactionCommandChain.class, chain);
    }

    /**
     * Teste la récupération de la chaîne de commandes Docker pour un langage non pris en charge.
     * Doit lancer une IllegalArgumentException.
     */
    @Test
    void getChain_withUnsupportedLanguage_shouldThrowIllegalArgumentException() {
        String language = "java";

        assertThrows(IllegalArgumentException.class, () -> factory.getChain(language));
    }

    /**
     * Teste la récupération de la chaîne de commandes Docker pour un langage vide.
     * Doit lancer une IllegalArgumentException.
     */
    @Test
    void getChain_withEmptyLanguage_shouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> factory.getChain(""));
    }
}