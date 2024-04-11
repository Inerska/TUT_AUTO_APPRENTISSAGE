import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.arobase.infrastructure.service.HashedPasswordService;

public class HashedPasswordServiceTest {

    @Test
    void testHashPassword() {
        String plainTextPassword = "arobase";
        HashedPasswordService hashedPasswordService = new HashedPasswordService();

        String hashedPassword = hashedPasswordService.hashPassword(plainTextPassword);

        assertTrue(hashedPassword != null && !hashedPassword.isEmpty());
    }

    @Test
    void testVerifyPassword() {
        String plainTextPassword = "arobase2";
        HashedPasswordService hashedPasswordService = new HashedPasswordService();
        String hashedPassword = hashedPasswordService.hashPassword(plainTextPassword);

        assertTrue(hashedPasswordService.verifyPassword(plainTextPassword, hashedPassword));

        assertFalse(hashedPasswordService.verifyPassword("differentPassword", hashedPassword));
    }
}
