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

    @Mock
    private AuthService authService;

    @Mock
    private BodyValidatorService bodyValidatorService;

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
        RegisterCredentialsDTO credentialsDTO = new RegisterCredentialsDTO("username", "mail@example.com", "password", "password");

        when(authService.register(credentialsDTO))
                .thenReturn(Uni.createFrom().item(new Account("mail@example.com", "password", "accessToken", "refreshToken")));

        Uni<Response> responseUni = registerController.register(credentialsDTO);

        Response response = responseUni.await().indefinitely();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

}
