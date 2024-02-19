import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.arobase.infrastructure.service.HashedPasswordService;

public class HashedPasswordServiceTest {

    // Classe de test pour la classe HashedPasswordService

    // Test du hashage d'un mot de passe
    @Test
    void testHashPassword() {
        // Given - Préparation des données
        String plainTextPassword = "password123";
        HashedPasswordService hashedPasswordService = new HashedPasswordService();

        // When - Exécution du test
        String hashedPassword = hashedPasswordService.hashPassword(plainTextPassword);

        // Then - Vérification que le mot de passe hashé n'est pas nul
        assertTrue(hashedPassword != null && !hashedPassword.isEmpty());
    }

    // Test de vérification d'un mot de passe
    @Test
    void testVerifyPassword() {
        // Given - Préparation des données
        String plainTextPassword = "password123";
        HashedPasswordService hashedPasswordService = new HashedPasswordService();
        String hashedPassword = hashedPasswordService.hashPassword(plainTextPassword);

        // When/Then - Vérification que le mot de passe correspond au hash
        assertTrue(hashedPasswordService.verifyPassword(plainTextPassword, hashedPassword));

        // When/Then - Vérification qu'un mot de passe différent ne correspond pas au hash
        assertFalse(hashedPasswordService.verifyPassword("differentPassword", hashedPassword));
    }
}
