package org.arobase.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The tokens DTO.
 * @param accessToken
 * @param refreshToken
 */
public record TokensDTO(@JsonProperty("access-token") String accessToken, @JsonProperty("refresh-token") String refreshToken) {
}
