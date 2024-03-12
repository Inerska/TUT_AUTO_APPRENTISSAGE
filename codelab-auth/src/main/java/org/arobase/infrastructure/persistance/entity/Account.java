package org.arobase.infrastructure.persistance.entity;

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
    private int id;

    /**
     * The email of the account.
     */
    @Column
    private String mail;

    /**
     * The password of the account.
     */
    @Column
    private String password;

    /**
     * The access token of the account.
     */
    @Column(name = "access_token", columnDefinition = "TEXT")
    private String accessToken;

    /**
     * The refresh token of the account.
     */
    @Column(name = "refresh_token")
    private String refreshToken;

    public Account(){}

    public Account(String mail, String password, String accessToken, String refreshToken) {
        this.mail = mail;
        this.password = password;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    /**
     * The mail getter.
     * @return The mail.
     */
    public String getMail() {
        return mail;
    }

    /**
     * The password getter.
     * @return The password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * The access token getter.
     * @return The access token.
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * The refresh token getter.
     * @return The refresh token.
     */
    public String getRefreshToken() {
        return refreshToken;
    }

    /**
     * The mail setter.
     * @param mail The mail.
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * The password setter.
     * @param password The password.
     */
    public void setPassword(String password) {
        this.password = password;

    }
    /**
     * The access token setter.
     * @param accessToken The access token.
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * The refresh token setter.
     * @param refreshToken The refresh token.
     */
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
