package org.arobase.infrastructure.persistence.entity;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

import java.util.ArrayList;
import java.util.List;

@MongoEntity(collection = "profiles")
public class Profile extends PanacheMongoEntity {

    public String username;

    public List<ExerciceResults> exercices = new ArrayList<>();

}
