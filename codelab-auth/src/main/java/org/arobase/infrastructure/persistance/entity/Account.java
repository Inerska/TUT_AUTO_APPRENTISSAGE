package org.arobase.infrastructure.persistance.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

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
     * The confirmation password of the account.
     */
    @Transient
    @JsonProperty("confirm-password")
    private String confirmPassword;

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

    /**
     * The phone of the account.
     */
    private String phone;

    public Account(){}

    public Account(String username, String password, String name, String surname, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    /**
     * The getter of the username.
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * The getter of the password.
     * @return The password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * The getter of the confirmation password.
     * @return The confirmation password.
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }

    /**
     * The getter of the name.
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * The getter of the surname.
     * @return The surname.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * The getter of the email.
     * @return The email.
     */
    public String getEmail() {
        return email;
    }
}
