import org.arobase.infrastructure.dto.TokensDTO;
import org.arobase.infrastructure.service.TokenManagementService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires pour la classe TokenManagementService.
 */
public class TokenManagementServiceTest {

    /**
     * Teste la méthode getTokens de TokenManagementService.
     * Vérifie qu'elle renvoie un objet TokensDTO non nul avec un access token et refresh token non nuls.
     */
    @Test
    void testGetTokens_ReturnsNonNullTokensDTO() {
        // Crée une instance du service de gestion de jetons
        TokenManagementService tokenManagementService = new TokenManagementService();

        // Appelle la méthode getTokens avec une adresse e-mail factice
        TokensDTO tokensDTO = tokenManagementService.getTokens("test@example.com");

        // Vérifie que l'objet retourné n'est pas nul
        assertNotNull(tokensDTO);
        // Vérifie que l'access token n'est pas nul
        assertNotNull(tokensDTO.accessToken());
        // Vérifie que le refresh token n'est pas nul
        assertNotNull(tokensDTO.refreshToken());
    }

    /**
     * Teste la méthode generateJwt de TokenManagementService.
     * Vérifie qu'elle génère un jeton JWT valide pour une adresse e-mail donnée.
     */
    @Test
    void testGenerateJwt_ValidJwtGenerated() {
        // Crée une instance du service de gestion de jetons
        TokenManagementService tokenManagementService = new TokenManagementService();
        // Adresse e-mail factice
        String mail = "test@example.com";

        String jwt = null;
        try {
            // Appelle la méthode generateJwt pour générer un jeton JWT
            jwt = tokenManagementService.generateJwt(mail);
        } catch (Exception e) {
            // En cas d'exception, le test échoue
            fail("Exception levée lors de la génération du JWT : " + e.getMessage());
        }

        // Vérifie que le jeton JWT généré n'est pas nul
        assertNotNull(jwt);
        // Vérifie que le jeton JWT généré n'est pas vide
        assertFalse(jwt.isEmpty());
    }

    /**
     * Teste la méthode generateRefreshToken de TokenManagementService.
     * Vérifie qu'elle génère un jeton de rafraîchissement valide.
     */
    @Test
    void testGenerateRefreshToken_ValidRefreshTokenGenerated() {
        // Crée une instance du service de gestion de jetons
        TokenManagementService tokenManagementService = new TokenManagementService();

        String refreshToken = null;
        try {
            // Appelle la méthode generateRefreshToken pour générer un jeton de rafraîchissement
            refreshToken = tokenManagementService.generateRefreshToken();
        } catch (Exception e) {
            // En cas d'exception, le test échoue
            fail("Exception levée lors de la génération du jeton de rafraîchissement : " + e.getMessage());
        }

        // Vérifie que le jeton de rafraîchissement généré n'est pas nul
        assertNotNull(refreshToken);
        // Vérifie que le jeton de rafraîchissement généré n'est pas vide
        assertFalse(refreshToken.isEmpty());
    }
}
