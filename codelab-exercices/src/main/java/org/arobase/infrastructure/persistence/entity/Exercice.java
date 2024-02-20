package org.arobase.infrastructure.persistence.entity;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

import java.time.LocalTime;

/**
 * The exercice entity.
 */
@MongoEntity(collection = "exercices")
public final class Exercice extends PanacheMongoEntity {

    /**
     * The test code.
     */
    public String testCode;

    /**
     * The author.
     */
    public String author;

    /**
     * The language.
     */
    public String language;

    /**
     * Created at.
     */
    public LocalTime createdAt;
}
