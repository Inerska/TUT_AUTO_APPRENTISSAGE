import io.smallrye.mutiny.Uni;
import org.arobase.infrastructure.dto.LoginCredentialsDTO;
import org.arobase.infrastructure.dto.RegisterCredentialsDTO;
import org.arobase.infrastructure.exception.AuthenticationException;
import org.arobase.infrastructure.dto.TokensDTO;
import org.arobase.infrastructure.persistance.entity.Account;
import org.arobase.infrastructure.persistance.repository.AccountRepository;
import org.arobase.infrastructure.service.AuthService;
import org.arobase.infrastructure.service.HashedPasswordService;
import org.arobase.infrastructure.service.TokenManagementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// à terminer

/**
 * Classe de test pour la classe AuthService.
 */
public class AuthServiceTest {

    // Mocks pour les dépendances de l'AuthService
    @Mock
    private AccountRepository accountRepository;

    @Mock
    private HashedPasswordService hashedPasswordService;

    @Mock
    private TokenManagementService tokenManagementService;

    // Injecte les mocks dans l'instance de l'AuthService
    @InjectMocks
    private AuthService authService;

    // Méthode exécutée avant chaque test pour initialiser les mocks
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Test de connexion lorsque le compte est trouvé et que le mot de passe correspond.
     */
    @Test
    public void testLogin_AccountFoundAndPasswordMatches() {
        String mail = "th30pelli@gmail.com";
        String password = "password";
        Account account = new Account(mail, password, "accessToken", "refreshToken");
        LoginCredentialsDTO credentialsDTO = new LoginCredentialsDTO(mail, password);

        // Définition des comportements simulés pour les mocks
        when(accountRepository.find("mail", mail)).thenAnswer(invocation -> Uni.createFrom().item(account));
        when(hashedPasswordService.verifyPassword(password, account.getPassword())).thenReturn(true);
        when(tokenManagementService.getTokens(mail)).thenReturn(new TokensDTO("accessToken", "refreshToken"));
        when(accountRepository.persistAndFlush(account)).thenReturn(Uni.createFrom().item(account));

        // Appel de la méthode à tester
        Uni<Account> result = authService.login(credentialsDTO);

        // Vérification des résultats du test
        assertNotNull(result, "Result should not be null");

        Account resultAccount = result.await().indefinitely();
        assertNotNull(resultAccount, "Result account should not be null");
        assertEquals(account, resultAccount, "Returned account should match");
    }

    /**
     * Test de connexion lorsque le compte n'est pas trouvé.
     */
    @Test
    public void testLogin_AccountNotFound() {
        String mail = "th30pelli@gmail.com";
        String password = "password";
        LoginCredentialsDTO credentialsDTO = new LoginCredentialsDTO(mail, password);

        // Définition du comportement simulé pour le mock
        when(accountRepository.find("mail", mail)).thenAnswer(invocation -> Uni.createFrom().nullItem());

        // Appel de la méthode à tester
        Uni<Account> result = authService.login(credentialsDTO);

        // Vérification des résultats du test
        assertThrows(AuthenticationException.class, () -> result.await().indefinitely(), "Should throw AuthenticationException for account not found");
    }

    /**
     * Test de connexion lorsque le mot de passe ne correspond pas.
     */
    @Test
    public void testLogin_PasswordDoesNotMatch() {
        String mail = "th30pelli@gmail.com";
        String password = "password";
        Account account = new Account(mail, password, "accessToken", "refreshToken");
        LoginCredentialsDTO credentialsDTO = new LoginCredentialsDTO(mail, password);

        // Définition des comportements simulés pour les mocks
        when(accountRepository.find("mail", mail)).thenAnswer(invocation -> Uni.createFrom().item(account));
        when(hashedPasswordService.verifyPassword(password, account.getPassword())).thenReturn(false);

        // Appel de la méthode à tester
        Uni<Account> result = authService.login(credentialsDTO);

        // Vérification des résultats du test
        assertThrows(AuthenticationException.class, () -> result.await().indefinitely(), "Should throw AuthenticationException for invalid password");
    }

    /**
     * Test d'enregistrement lorsque le compte existe déjà.
     */
    @Test
    public void testRegister_AccountAlreadyExists() {
        String mail = "th30pelli@gmail.com";
        RegisterCredentialsDTO credentialsDTO = new RegisterCredentialsDTO("username", mail, "password", "password");
        Account existingAccount = new Account(mail, "hashedPassword", "accessToken", "refreshToken");

        // Définition du comportement simulé pour le mock
        when(accountRepository.find("mail", mail)).thenAnswer(invocation -> Uni.createFrom().item(existingAccount));

        // Appel de la méthode à tester
        Uni<Account> result = authService.register(credentialsDTO);

        // Vérification des résultats du test
        assertThrows(AuthenticationException.class, () -> result.await().indefinitely(), "Should throw AuthenticationException for account already exists");
    }

    @Test
    public void testRegister_PasswordsDoNotMatch() {
        String mail = "th30pelli@gmail.com";
        RegisterCredentialsDTO credentialsDTO = new RegisterCredentialsDTO("username", mail, "password1", "password2");

        // Appel de la méthode à tester
        assertThrows(AuthenticationException.class, () -> authService.register(credentialsDTO), "Should throw AuthenticationException for passwords do not match");
    }

    @Test
    public void testRegister_Success() {
        String mail = "th30pelli@gmail.com";
        String password = "password";
        RegisterCredentialsDTO credentialsDTO = new RegisterCredentialsDTO("username", mail, password, password);

        // Définition des comportements simulés pour les mocks
        when(accountRepository.find("mail", mail)).thenAnswer(invocation -> Uni.createFrom().nullItem());
        when(hashedPasswordService.hashPassword(password)).thenReturn("hashedPassword");
        when(tokenManagementService.getTokens(mail)).thenReturn(new TokensDTO("accessToken", "refreshToken"));
        when(accountRepository.persistAndFlush(any(Account.class))).thenAnswer(invocation -> {
            Account account = invocation.getArgument(0);
            return Uni.createFrom().item(account);
        });

        // Appel de la méthode à tester
        Uni<Account> result = authService.register(credentialsDTO);

        // Vérification des résultats du test
        assertNotNull(result, "Result should not be null");
        assertDoesNotThrow(() -> result.await().indefinitely(), "Should not throw exception");
    }

}
