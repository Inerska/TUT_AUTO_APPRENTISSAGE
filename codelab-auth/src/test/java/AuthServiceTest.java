import io.quarkus.hibernate.reactive.panache.PanacheQuery;
import io.smallrye.mutiny.Uni;
import org.arobase.infrastructure.dto.LoginCredentialsDTO;
import org.arobase.infrastructure.exception.AuthenticationException;
import org.arobase.infrastructure.persistance.entity.Account;
import org.arobase.infrastructure.persistance.repository.AccountRepository;
import org.arobase.infrastructure.service.AuthService;
import org.arobase.infrastructure.service.HashedPasswordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class AuthServiceTest {

    @InjectMocks
    AuthService authService;

    @Mock
    AccountRepository accountRepository;

    @Mock
    HashedPasswordService hashedPasswordService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLogin_AccountNotFound() {
        LoginCredentialsDTO loginCredentials = new LoginCredentialsDTO("nonexistent@example.com", "password");
        when(accountRepository.find("mail", loginCredentials.mail())).thenReturn(Mockito.mock(PanacheQuery.class));
        when(accountRepository.find("mail", loginCredentials.mail()).firstResult()).thenReturn(Uni.createFrom().nullItem());

        assertThrows(AuthenticationException.class, () -> authService.login(loginCredentials).await().indefinitely());
    }

    @Test
    public void testLogin_InvalidPassword() {
        LoginCredentialsDTO loginCredentials = new LoginCredentialsDTO("test@example.com", "invalidPassword");
        Account account = new Account("test@example.com", "hashedPassword", "accessToken", "refreshToken");
        when(accountRepository.find("mail", loginCredentials.mail())).thenReturn(Mockito.mock(PanacheQuery.class));
        when(accountRepository.find("mail", loginCredentials.mail()).firstResult()).thenReturn(Uni.createFrom().item(account));
        when(hashedPasswordService.verifyPassword(loginCredentials.password(), account.getPassword())).thenReturn(false);

        assertThrows(AuthenticationException.class, () -> authService.login(loginCredentials).await().indefinitely());
    }
}
