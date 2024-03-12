package org.arobase.infrastructure.persistence.entity;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

/**
 * Task to do during an exercise
 */
@MongoEntity(collection = "tasks")
public final class Task extends PanacheMongoEntity {

    /**
     * The task description.
     */
    public String content;

    /**
     * The order of the task.
     */
    public int order;

}
