import org.arobase.infrastructure.dto.LoginCredentialsDTO;
import org.arobase.infrastructure.service.AuthService;
import org.arobase.infrastructure.service.BodyValidatorService;
import org.arobase.web.controller.LoginController;
import org.arobase.infrastructure.persistance.entity.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.opentest4j.AssertionFailedError;

import io.smallrye.mutiny.Uni;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import jakarta.ws.rs.core.Response;

// à terminer

/**
 * Classe de test pour le contrôleur de connexion (LoginController).
 */
public class LoginControllerTest {

    // Mock du service d'authentification
    @Mock
    private AuthService authService;

    // Mock du service de validation du corps de la requête
    @Mock
    private BodyValidatorService bodyValidatorService;

    // Injecte les mocks dans le contrôleur de connexion
    @InjectMocks
    private LoginController loginController;

    /**
     * Méthode exécutée avant chaque test pour initialiser les mocks.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Teste le cas où des identifiants valides sont fournis et où l'authentification réussit.
     */
    @Test
    public void testLogin_ValidCredentials_Success() {
        // Préparation des données de test
        LoginCredentialsDTO credentialsDTO = new LoginCredentialsDTO("username", "password");

        // Définition du comportement attendu pour le service d'authentification
        when(authService.login(credentialsDTO))
                .thenReturn(Uni.createFrom().item(new Account(null, null, "accessToken", "refreshToken")));

        // Exécution de la méthode à tester
        Response response = loginController.login(credentialsDTO).await().indefinitely();

        // Vérification des résultats
        assertTrue(response != null);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertTrue(response.getEntity() != null);

        // Vérification que les méthodes du service d'authentification et du service de validation du corps
        // de la requête ont été appelées une seule fois chacune
        verify(authService, times(1)).login(credentialsDTO);
        verify(bodyValidatorService, times(1)).validateBody(credentialsDTO);
    }

    /*
    @Test
    public void testLogin_InvalidCredentials_ReturnsUnauthorized() {
        // Préparation des données de test
        LoginCredentialsDTO credentialsDTO = new LoginCredentialsDTO("username", "wrongPassword");

        // Définition du comportement attendu pour le service d'authentification
        when(authService.login(credentialsDTO))
                .thenReturn(Uni.createFrom().nullItem());

        // Exécution de la méthode à tester
        try {
            Response response = loginController.login(credentialsDTO).await().indefinitely();

            // Vérification des résultats
            assertTrue(response != null);
            assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus());

            // Vérification que les méthodes du service d'authentification et du service de validation du corps
            // de la requête ont été appelées une seule fois chacune
            verify(authService, times(1)).login(credentialsDTO);
            verify(bodyValidatorService, times(1)).validateBody(credentialsDTO);
        } catch (AssertionError e) {
            System.out.println("L'exception AssertionError a été levée : " + e.getMessage());
            throw e;
        } catch (Throwable t) {
            System.out.println("L'exception " + t.getClass().getSimpleName() + " a été levée : " + t.getMessage());
            throw new AssertionError("Une exception inattendue a été levée : " + t.getClass().getSimpleName());
        }
    }

    @Test
    public void testLogin_NullCredentials_ThrowsIllegalArgumentException() {
        // Préparation des données de test
        LoginCredentialsDTO credentialsDTO = null;

        // Exécution de la méthode à tester et vérification de l'exception levée
        try {
            assertThrows(IllegalArgumentException.class, () -> loginController.login(credentialsDTO));

            // Vérification que les méthodes du service d'authentification et du service de validation du corps
            // de la requête n'ont pas été appelées
            verify(authService, never()).login(credentialsDTO);
            verify(bodyValidatorService, never()).validateBody(credentialsDTO);
        } catch (AssertionError e) {
            System.out.println("L'exception AssertionError a été levée : " + e.getMessage());
            throw e;
        } catch (Throwable t) {
            System.out.println("L'exception " + t.getClass().getSimpleName() + " a été levée : " + t.getMessage());
            throw new AssertionError("Une exception inattendue a été levée : " + t.getClass().getSimpleName());
        }
    }
     */
}
