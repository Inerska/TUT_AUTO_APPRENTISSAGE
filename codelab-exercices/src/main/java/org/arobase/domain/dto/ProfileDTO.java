package org.arobase.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProfileDTO(@JsonProperty("username") String username) {

}
