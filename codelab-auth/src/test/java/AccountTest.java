import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.arobase.infrastructure.persistance.entity.Account;

public class AccountTest {

    @Test
    void testDefaultConstructor() {
        Account account = new Account();

        assertNull(account.getMail());
        assertNull(account.getPassword());
        assertNull(account.getAccessToken());
        assertNull(account.getRefreshToken());
    }

    @Test
    void testParameterizedConstructor() {
        String mail = "th30pelli@gmail.com";
        String password = "TheoIUTInfo";
        String accessToken = "accessToken";
        String refreshToken = "refreshToken";

        Account account = new Account(mail, password, accessToken, refreshToken);

        assertEquals(mail, account.getMail());
        assertEquals(password, account.getPassword());
        assertEquals(accessToken, account.getAccessToken());
        assertEquals(refreshToken, account.getRefreshToken());
    }

    @Test
    void testAccessors() {
        Account account = new Account();

        account.setMail("th30pelli@gmail.com");
        account.setPassword("TheoIUTInfo");
        account.setAccessToken("accessToken");
        account.setRefreshToken("refreshToken");

        assertEquals("th30pelli@gmail.com", account.getMail());
        assertEquals("TheoIUTInfo", account.getPassword());
        assertEquals("accessToken", account.getAccessToken());
        assertEquals("refreshToken", account.getRefreshToken());
    }

    @Test
    void testSetAccessToken() {
        Account account = new Account();

        account.setAccessToken("accessToken");

        assertEquals("accessToken", account.getAccessToken());
    }

    @Test
    void testSetRefreshToken() {
        Account account = new Account();

        account.setRefreshToken("refreshToken");

        assertEquals("refreshToken", account.getRefreshToken());
    }

    @Test
    void testGetMail() {
        Account account = new Account();

        account.setMail("theopelli54@gmail.com");

        assertEquals("theopelli54@gmail.com", account.getMail());
    }

    @Test
    void testGetPassword() {
        Account account = new Account();

        account.setPassword("Pelli54");

        assertEquals("Pelli54", account.getPassword());
    }
}
