package org.arobase.infrastructure.authentication.service;

import jakarta.inject.Singleton;
import org.mindrot.jbcrypt.BCrypt;

/**
 * The hashed password service.a
 */
@Singleton
public class HashedPasswordService {

    /**
     * The hash password method.
     * @param plainTextPassword The plain text password.
     * @return The hashed password.
     */
    public String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    /**
     * The verify password method.
     * @param plainTextPassword The plain text password.
     * @param hashedPassword The hashed password.
     * @return The hashed password.
     */
    public boolean verifyPassword(String plainTextPassword, String hashedPassword) {
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }
}
