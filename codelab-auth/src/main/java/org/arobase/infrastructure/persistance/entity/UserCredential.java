package org.arobase.infrastructure.persistance.entity;

/**
 * The user credential entity.
 */
public class UserCredential {

    /**
     * The username of the user credential.
     */
    private String username;

    /**
     * The password of the user credential.
     */
    private String password;

    /**
     * The constructor.
     * @return The username of the user credential.
     */
    public String getUsername() {
        return username;
    }

    /**
     * The constructor.
     * @return The password of the user credential.
     */
    public String getPassword() {
        return password;
    }
}
