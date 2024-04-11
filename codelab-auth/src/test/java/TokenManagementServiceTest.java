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
        TokenManagementService tokenManagementService = new TokenManagementService();

        TokensDTO tokensDTO = tokenManagementService.getTokens("test@example.com");

        assertNotNull(tokensDTO);
        assertNotNull(tokensDTO.accessToken());
        assertNotNull(tokensDTO.refreshToken());
    }

    /**
     * Teste la méthode generateJwt de TokenManagementService.
     * Vérifie qu'elle génère un jeton JWT valide pour une adresse e-mail donnée.
     */
    @Test
    void testGenerateJwt_ValidJwtGenerated() {
        TokenManagementService tokenManagementService = new TokenManagementService();
        String mail = "test@example.com";

        String jwt = null;
        try {
            jwt = tokenManagementService.generateJwt(mail);
        } catch (Exception e) {
            fail("Exception levée lors de la génération du JWT : " + e.getMessage());
        }

        assertNotNull(jwt);
        assertFalse(jwt.isEmpty());
    }

    /**
     * Teste la méthode generateRefreshToken de TokenManagementService.
     * Vérifie qu'elle génère un jeton de rafraîchissement valide.
     */
    @Test
    void testGenerateRefreshToken_ValidRefreshTokenGenerated() {
        TokenManagementService tokenManagementService = new TokenManagementService();

        String refreshToken = null;
        try {
            refreshToken = tokenManagementService.generateRefreshToken();
        } catch (Exception e) {
            fail("Exception levée lors de la génération du jeton de rafraîchissement : " + e.getMessage());
        }

        assertNotNull(refreshToken);
        assertFalse(refreshToken.isEmpty());
    }
}
