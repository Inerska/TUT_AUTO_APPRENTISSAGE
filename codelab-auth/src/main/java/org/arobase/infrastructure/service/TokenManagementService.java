package org.arobase.infrastructure.service;

import io.smallrye.jwt.build.Jwt;
import jakarta.inject.Singleton;
import org.arobase.infrastructure.dto.TokensDTO;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.Base64;

/**
 * The token management service.
 */
@Singleton
public class TokenManagementService {

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
    private String generateJwt(String mail){
        Instant now = Instant.now();
        Instant expirationTime = now.plusSeconds(3600);

        return Jwt.upn(mail)
                .expiresAt(expirationTime)
                .sign();
    }

    /**
     * The generateRefreshToken method.
     * @return The refresh token.
     */
    private String generateRefreshToken() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] bytes = new byte[64];
        secureRandom.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }
}
