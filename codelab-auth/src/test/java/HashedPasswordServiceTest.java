import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.arobase.infrastructure.service.HashedPasswordService;

public class HashedPasswordServiceTest {

    // Classe de test pour la classe HashedPasswordService

    // Test du hashage d'un mot de passe
    @Test
    void testHashPassword() {
        // Préparation des données
        String plainTextPassword = "arobase";
        HashedPasswordService hashedPasswordService = new HashedPasswordService();

        // Exécution du test
        String hashedPassword = hashedPasswordService.hashPassword(plainTextPassword);

        // Vérification que le mot de passe hashé n'est pas nul
        assertTrue(hashedPassword != null && !hashedPassword.isEmpty());
    }

    // Test de vérification d'un mot de passe
    @Test
    void testVerifyPassword() {
        // Préparation des données
        String plainTextPassword = "arobase2";
        HashedPasswordService hashedPasswordService = new HashedPasswordService();
        String hashedPassword = hashedPasswordService.hashPassword(plainTextPassword);

        // Vérification que le mot de passe correspond au hash
        assertTrue(hashedPasswordService.verifyPassword(plainTextPassword, hashedPassword));

        // Vérification qu'un mot de passe différent ne correspond pas au hash
        assertFalse(hashedPasswordService.verifyPassword("differentPassword", hashedPassword));
    }
}
