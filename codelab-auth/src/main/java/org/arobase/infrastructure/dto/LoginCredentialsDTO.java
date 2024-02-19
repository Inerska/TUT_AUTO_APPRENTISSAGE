package org.arobase.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The login credentials DTO.
 * @param mail
 * @param password
 */
public record LoginCredentialsDTO(@JsonProperty("mail") String mail, @JsonProperty("password") String password) {

}
