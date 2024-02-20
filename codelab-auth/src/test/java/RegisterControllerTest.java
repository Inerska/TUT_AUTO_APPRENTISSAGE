import org.arobase.infrastructure.dto.RegisterCredentialsDTO;
import org.arobase.infrastructure.service.AuthService;
import org.arobase.infrastructure.service.BodyValidatorService;
import org.arobase.web.controller.RegisterController;
import org.arobase.infrastructure.persistance.entity.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.smallrye.mutiny.Uni;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import jakarta.ws.rs.core.Response;

/**
 * Classe de test pour le contrôleur d'enregistrement (RegisterController).
 */
public class RegisterControllerTest {

    // Mock du service d'authentification
    @Mock
    private AuthService authService;

    // Mock du service de validation du corps de la requête
    @Mock
    private BodyValidatorService bodyValidatorService;

    // Injecte les mocks dans le contrôleur d'enregistrement
    @InjectMocks
    private RegisterController registerController;

    /**
     * Méthode exécutée avant chaque test pour initialiser les mocks.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Teste le cas où l'enregistrement réussit.
     */
    @Test
    public void testRegister_Success() {
        // Préparation des données de test
        RegisterCredentialsDTO credentialsDTO = new RegisterCredentialsDTO("username", "mail@example.com", "password", "password");

        // Définition du comportement attendu pour le service d'authentification
        when(authService.register(credentialsDTO))
                .thenReturn(Uni.createFrom().item(new Account("mail@example.com", "password", "accessToken", "refreshToken")));

        // Exécution de la méthode à tester
        Uni<Response> responseUni = registerController.register(credentialsDTO);

        // Vérification des résultats
        Response response = responseUni.await().indefinitely();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    /* Teste le cas où l'enregistrement échoue.
    @Test
    public void testRegister_Failure() {
        // Préparation des données de test
        RegisterCredentialsDTO credentialsDTO = new RegisterCredentialsDTO("username", "mail@example.com", "password", "password");

        // Définition du comportement attendu pour le service d'authentification
        when(authService.register(credentialsDTO))
                .thenReturn(Uni.createFrom().nullItem()); // Simule un échec

        // Exécution de la méthode à tester
        Uni<Response> responseUni = registerController.register(credentialsDTO);

        // Vérification des résultats
        Response response = responseUni.await().indefinitely();
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
    }*/

}
