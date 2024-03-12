package org.arobase.infrastructure.persistence.entity;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

/**
 * The difficulty entity.
 */
@MongoEntity(collection = "difficulties")
public class Difficulty extends PanacheMongoEntity {

    /**
     * The difficulty name.
     */
    public String name;

}
