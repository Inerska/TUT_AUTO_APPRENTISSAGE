package org.arobase.infrastructure.persistence.entity;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

/**
 * The difficulty entity.
 */
@MongoEntity(collection = "difficulties")
public class Difficulty extends PanacheMongoEntity {

    /**
     * The difficulty name.
     */
    public String name;

    public String getName() {
        return name;
    }
}
