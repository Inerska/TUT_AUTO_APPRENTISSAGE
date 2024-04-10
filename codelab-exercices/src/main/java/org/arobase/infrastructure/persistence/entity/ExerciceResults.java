package org.arobase.infrastructure.persistence.entity;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

import java.time.LocalDateTime;

@MongoEntity(collection = "exercice-results")
public class ExerciceResults extends PanacheMongoEntity {
    public String status;
    public String result;
    public String exerciceId;
    public LocalDateTime timestamp = LocalDateTime.now();
    public String errorDetails;
    public String additionalInfo;
}
