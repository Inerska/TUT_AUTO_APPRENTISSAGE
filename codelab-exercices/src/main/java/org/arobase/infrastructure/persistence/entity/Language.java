package org.arobase.infrastructure.persistence.entity;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

/**
 * The language entity.
 */
@MongoEntity(collection = "languages")
public class Language extends PanacheMongoEntity {

    public String name;

}
