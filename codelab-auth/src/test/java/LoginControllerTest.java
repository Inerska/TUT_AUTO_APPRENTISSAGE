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

import io.smallrye.mutiny.Uni;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import jakarta.ws.rs.core.Response;


/**
 * Classe de test pour le contrôleur de connexion (LoginController).
 */
public class LoginControllerTest {

    @Mock
    private AuthService authService;

    @Mock
    private BodyValidatorService bodyValidatorService;
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
        LoginCredentialsDTO credentialsDTO = new LoginCredentialsDTO("username", "password");

        when(authService.login(credentialsDTO))
                .thenReturn(Uni.createFrom().item(new Account(null, null, "accessToken", "refreshToken")));

        Response response = loginController.login(credentialsDTO).await().indefinitely();

        assertTrue(response != null);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertTrue(response.getEntity() != null);

        verify(authService, times(1)).login(credentialsDTO);
        verify(bodyValidatorService, times(1)).validateBody(credentialsDTO);
    }
}
