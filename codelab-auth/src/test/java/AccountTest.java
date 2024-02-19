import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.arobase.infrastructure.persistance.entity.Account;

public class AccountTest {

    // Classe de test pour la classe Account

    // Test du constructeur par défaut
    @Test
    void testDefaultConstructor() {
        // Given - Préparation des données
        Account account = new Account();

        // When - Exécution du test

        // Then - Vérification des valeurs par défaut
        assertEquals(null, account.getMail());
        assertEquals(null, account.getPassword());
        assertEquals(null, account.getAccessToken());
        assertEquals(null, account.getRefreshToken());
    }

    // Test du constructeur paramétré
    @Test
    void testParameterizedConstructor() {
        // Given - Préparation des données
        String mail = "th30pelli@gmail.com";
        String password = "TheoIUTInfo";
        String accessToken = "accessToken";
        String refreshToken = "refreshToken";

        // When - Exécution du test
        Account account = new Account(mail, password, accessToken, refreshToken);

        // Then - Vérification des valeurs initialisées
        assertEquals(mail, account.getMail());
        assertEquals(password, account.getPassword());
        assertEquals(accessToken, account.getAccessToken());
        assertEquals(refreshToken, account.getRefreshToken());
    }

    // Test des accesseurs
    @Test
    void testAccessors() {
        // Given - Préparation des données
        Account account = new Account();

        // When - Exécution du test
        account.setMail("th30pelli@gmail.com");
        account.setPassword("TheoIUTInfo");
        account.setAccessToken("accessToken");
        account.setRefreshToken("refreshToken");

        // Then - Vérification des valeurs des accesseurs
        assertEquals("th30pelli@gmail.com", account.getMail());
        assertEquals("TheoIUTInfo", account.getPassword());
        assertEquals("accessToken", account.getAccessToken());
        assertEquals("refreshToken", account.getRefreshToken());
    }

    // Test du setter de AccessToken
    @Test
    void testSetAccessToken() {
        // Given - Préparation des données
        Account account = new Account();

        // When - Exécution du test
        account.setAccessToken("accessToken");

        // Then - Vérification du setter de AccessToken
        assertEquals("accessToken", account.getAccessToken());
    }

    // Test du setter de RefreshToken
    @Test
    void testSetRefreshToken() {
        // Given - Préparation des données
        Account account = new Account();

        // When - Exécution du test
        account.setRefreshToken("refreshToken");

        // Then - Vérification du setter de RefreshToken
        assertEquals("refreshToken", account.getRefreshToken());
    }

    // Test du getter de Mail
    @Test
    void testGetMail() {
        // Given - Préparation des données
        Account account = new Account();

        // When - Exécution du test
        account.setMail("theopelli54@gmail.com");

        // Then - Vérification du getter de Mail
        assertEquals("theopelli54@gmail.com", account.getMail());
    }

    // Test du getter de Password
    @Test
    void testGetPassword() {
        // Given - Préparation des données
        Account account = new Account();

        // When - Exécution du test
        account.setPassword("Pelli54");

        // Then - Vérification du getter de Password
        assertEquals("Pelli54", account.getPassword());
    }
}
