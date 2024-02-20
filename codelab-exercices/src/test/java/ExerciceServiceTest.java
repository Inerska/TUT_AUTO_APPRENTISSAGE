import org.arobase.domain.model.request.ExerciceSubmitRequest;
import org.arobase.infrastructure.persistence.entity.Exercice;
import org.arobase.infrastructure.persistence.entity.ExerciceResults;
import org.arobase.infrastructure.persistence.repository.ExerciceRepository;
import org.arobase.infrastructure.persistence.repository.ExerciceResultsRepository;
import org.arobase.infrastructure.persistence.service.ExerciceService;
import org.bson.types.ObjectId;
import org.jboss.logging.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

// à terminer

/**
 * Classe de test pour la classe ExerciceService.
 */
class ExerciceServiceTest {

    @Mock
    private ExerciceResultsRepository exerciceResultsRepository;

    @Mock
    private ExerciceRepository exerciceRepository;

    @Mock
    private Logger logger;

    @Mock
    private ExerciceSubmitRequest exerciceSubmitRequest;

    @Mock
    private ExerciceResults exerciceResults;

    @InjectMocks
    private ExerciceService exerciceService;

    /**
     * Méthode exécutée avant chaque test.
     * Initialise les mocks et les instances nécessaires.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Teste la récupération du résultat d'un exercice par ID.
     * Vérifie qu'un résultat d'exercice est trouvé pour un ID valide.
     */
    @Test
    void testGetExerciceResultById_ValidId_ExerciceResultFound() {
        // Given: Préparation des données nécessaires pour le test
        String validId = "5feb56b8f1f4e56528b6cd28";
        when(exerciceResultsRepository.findByIdOptional(any(ObjectId.class)))
                .thenReturn(Optional.of(new ExerciceResults()));

        // When: Exécution de la méthode à tester
        Optional<ExerciceResults> result = exerciceService.getExerciceResultById(validId);

        // Then: Vérification des résultats ou du comportement attendu
        assertTrue(result.isPresent());
    }

    /**
     * Teste le traitement du résultat d'un exercice par ID.
     * Vérifie qu'aucune exception n'est levée pour un ID valide.
     */
    /*@Test
    void testProcessExerciceResultById_ValidId_ExerciceResultProcessed() {
        // Given: Préparation des données nécessaires pour le test
        String validId = "5feb56b8f1f4e56528b6cd28";
        when(exerciceResultsRepository.findByIdOptional(any(ObjectId.class)))
                .thenReturn(Optional.of(new ExerciceResults()));

        // When: Exécution de la méthode à tester
        assertDoesNotThrow(() -> exerciceService.processExerciceResultById(validId));
    }

    /**
     * Teste le traitement du résultat d'un exercice par ID.
     * Vérifie qu'une NotFoundException est levée pour un ID invalide.
     */
    /*@Test
    void testProcessExerciceResultById_InvalidId_ExerciceResultNotFound() {
        // Given: Préparation des données nécessaires pour le test
        String invalidId = "invalidId";
        when(exerciceResultsRepository.findByIdOptional(any(ObjectId.class))).thenReturn(Optional.empty());

        // Then: Vérification des résultats ou du comportement attendu
        assertThrows(NotFoundException.class, () -> {
            // When: Exécution de la méthode à tester
            exerciceService.processExerciceResultById(invalidId);
        });
    }

    /**
     * Teste la mise à jour du résultat d'un exercice.
     * Vérifie qu'aucune exception n'est levée lors de la mise à jour du résultat.
     */
    /*@Test
    void testUpdateExerciceResult() {
        // Given: Préparation des données nécessaires pour le test
        String id = "5feb56b8f1f4e56528b6cd28";
        String status = "UPDATED";
        String result = "New result";

        // Mock repository response
        when(exerciceResultsRepository.findByIdOptional(any(ObjectId.class))).thenReturn(Optional.of(new ExerciceResults()));

        // When: Exécution de la méthode à tester
        assertDoesNotThrow(() -> exerciceService.updateExerciceResult(id, status, result));
    }

    /**
     * Teste la récupération du code de test par ID d'exercice.
     * Vérifie que le code de test est récupéré avec succès pour un ID d'exercice valide.
     */
    @Test
    void testGetTestCodeByExerciceId() {
        // Given: Préparation des données nécessaires pour le test
        String id = "5feb56b8f1f4e56528b6cd28";
        Exercice exercice = new Exercice();
        exercice.setTestCode("Some test code");

        // Mock repository response
        when(exerciceRepository.findByIdOptional(any(ObjectId.class))).thenReturn(Optional.of(exercice));

        // When: Exécution de la méthode à tester
        String testCode = exerciceService.getTestCodeByExerciceId(id);

        // Then: Vérification des résultats ou du comportement attendu
        assertEquals("Some test code", testCode);
    }
}
