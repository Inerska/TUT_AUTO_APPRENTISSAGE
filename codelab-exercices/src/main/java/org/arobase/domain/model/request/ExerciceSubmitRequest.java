package org.arobase.domain.model.request;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Represents an exercice request.
 */
@JsonSerialize
public class ExerciceSubmitRequest implements MessagingRequest {
    private final String code;
    private final String language;
    private final String exerciceId;
    private final String profileId;
    private String exerciceResultObjectId;

    public ExerciceSubmitRequest(String code, String language, String exerciceId, String profileId, String exerciceResultObjectId) {
        this.code = code;
        this.language = language;
        this.exerciceId = exerciceId;
        this.profileId = profileId;
        this.exerciceResultObjectId = exerciceResultObjectId;
    }

    public String getExerciceResultObjectId() {
        return exerciceResultObjectId;
    }

    public void setExerciceResultObjectId(String exerciceResultObjectId) {
        this.exerciceResultObjectId = exerciceResultObjectId;
    }

    @Override
    public String toString() {
        return "ExerciceRequest{" +
                "code='" + code + '\'' +
                ", language='" + language + '\'' +
                ", id='" + exerciceId + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public String getLanguage() {
        return language;
    }

    public String getExerciceId() {
        return exerciceId;
    }

    public String getProfileId() {
        return profileId;
    }
}
