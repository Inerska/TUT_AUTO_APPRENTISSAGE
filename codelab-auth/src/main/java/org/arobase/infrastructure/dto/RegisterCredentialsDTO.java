package org.arobase.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The register credentials DTO.
 * @param username
 * @param mail
 * @param password
 * @param confirmPassword
 */
public record RegisterCredentialsDTO(@JsonProperty("username") String username, @JsonProperty("mail") String mail, @JsonProperty("password") String password, @JsonProperty("confirm-password") String confirmPassword) {

}
