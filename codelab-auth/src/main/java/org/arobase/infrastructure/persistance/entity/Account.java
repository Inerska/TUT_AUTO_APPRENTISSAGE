package org.arobase.infrastructure.persistance.entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
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
     * The profile id of the account.
     */
    @Column
    private String profileId;

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

    public Account(String mail, String password, String profileId, String accessToken, String refreshToken) {
        this.mail = mail;
        this.password = password;
        this.profileId = profileId;
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
     * The profile id getter.
     * @return The profile id.
     */
    public String getProfileId() {
        return profileId;
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
