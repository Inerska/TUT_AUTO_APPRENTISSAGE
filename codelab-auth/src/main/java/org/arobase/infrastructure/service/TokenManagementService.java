package org.arobase.infrastructure.service;

import io.smallrye.jwt.build.Jwt;
import jakarta.inject.Singleton;
import org.arobase.infrastructure.dto.TokensDTO;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Base64;

/**
 * The token management service.
 */
@Singleton
public class TokenManagementService {

    private static final KeyPair KEY_PAIR = generateKeyPair();

    public TokensDTO getTokens(String mail) {
        String accessToken = generateJwt(mail);
        String refreshToken = generateRefreshToken();
        return new TokensDTO(accessToken, refreshToken);
    }

    /**
     * The generateJwt method.
     * @param mail The mail.
     * @return The jwt token.
     */
    public String generateJwt(String mail){
        Instant now = Instant.now();
        Instant expirationTime = now.plusSeconds(3600);

        return Jwt.upn(mail)
                .expiresAt(expirationTime)
                .sign(KEY_PAIR.getPrivate());
    }

    /**
     * The generateRefreshToken method.
     * @return The refresh token.
     */
    public String generateRefreshToken() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] bytes = new byte[64];
        secureRandom.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    /**
     * Generate a pair of RSA keys.
     * @return The key pair.
     */
    private static KeyPair generateKeyPair() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            return keyPairGenerator.generateKeyPair();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la génération de la paire de clés RSA", e);
        }
    }
}
