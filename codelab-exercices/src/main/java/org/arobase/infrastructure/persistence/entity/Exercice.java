package org.arobase.infrastructure.persistence.entity;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

/**
 * The exercice entity.
 */
@MongoEntity(collection = "exercices")
public class Exercice extends PanacheMongoEntity {

    /**
     * The tilted of the exercice.
     */
    private String tilted;

    /**
     * The path of the correction file
     */
    private String correctionFilePath;
}
