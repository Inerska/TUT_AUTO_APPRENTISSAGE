package org.arobase.domain.model.request;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public record DifficultyCreateRequest(String name) implements MessagingRequest {

}
