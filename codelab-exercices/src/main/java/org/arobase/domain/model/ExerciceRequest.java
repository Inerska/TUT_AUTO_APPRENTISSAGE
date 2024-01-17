package org.arobase.domain.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Represents an exercice request.
 */
@JsonSerialize
public class ExerciceRequest implements MessagingRequest {
    private final String code;
    private final String language;
    private final String title;

    public ExerciceRequest(String code, String language, String title) {
        this.code = code;
        this.language = language;
        this.title = title;
    }

    @Override
    public String toString() {
        return "ExerciceRequest{" +
                "code='" + code + '\'' +
                ", language='" + language + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public String getLanguage() {
        return language;
    }

    public String getTitle() {
        return title;
    }
}
