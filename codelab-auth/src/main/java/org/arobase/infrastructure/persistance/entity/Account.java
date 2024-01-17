package org.arobase.infrastructure.persistance.entity;

import jakarta.persistence.*;
import net.bytebuddy.asm.Advice;

/**
 * The account entity.
 */
@Entity
public class Account {

    /**
     * The id of the account.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * The username of the account.
     */
    private String username;

    /**
     * The password of the account.
     */
    private String password;

    /**
     * The name of the account.
     */
    private String name;

    /**
     * The surname of the account.
     */
    private String surname;

    /**
     * The email of the account.
     */
    private String email;

    public Account(){};

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
