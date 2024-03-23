package org.arobase.domain.model.request;

public record LanguageCreateRequest(String name, String abbreviation) implements MessagingRequest {

}
