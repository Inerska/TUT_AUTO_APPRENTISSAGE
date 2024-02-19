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

public class LoginControllerTest {

    @Mock
    private AuthService authService;

    @Mock
    private BodyValidatorService bodyValidatorService;

    @InjectMocks
    private LoginController loginController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testLogin_ValidCredentials_Success() {
        // Given
        LoginCredentialsDTO credentialsDTO = new LoginCredentialsDTO("username", "password");
        when(authService.login(credentialsDTO))
                .thenReturn(Uni.createFrom().item(new Account(null, null, "accessToken", "refreshToken")));

        // When
        Response response = loginController.login(credentialsDTO).await().indefinitely();

        // Then
        assertTrue(response != null);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertTrue(response.getEntity() != null);

        // Verify that authService.login() and bodyValidatorService.validateBody() are called once
        verify(authService, times(1)).login(credentialsDTO);
        verify(bodyValidatorService, times(1)).validateBody(credentialsDTO);
    }

}
