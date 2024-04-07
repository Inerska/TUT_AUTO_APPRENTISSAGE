package org.arobase.infrastructure.persistence.entity;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * The exercice entity.
 */
@MongoEntity(collection = "exercices")
public final class Exercice extends PanacheMongoEntity {

    /**
     * The title.
     */
    public String title;

    /**
     * The description.
     */
    public String description;

    /**
     * The Instructions.
     */
    public String instructions;

    /**
     * The tasks.
     */
    public List<Task> tasks;

    /**
     * The banner, url.
     */
    public String banner;

    /**
     * The author.
     */
    public String author;

    /**
     * The test code.
     */
    public String testCode;

    /**
     * The language.
     */
    public Language language;

    /**
     * The difficulty.
     */
    public Difficulty difficulty;

    /**
     * The number of tests.
     */
    public int nbTests;

    /**
     * Created at.
     */
    public LocalDateTime createdAt;
}
