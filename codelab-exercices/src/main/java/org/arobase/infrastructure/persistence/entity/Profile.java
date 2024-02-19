package org.arobase.infrastructure.persistence.entity;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

import java.util.ArrayList;
import java.util.List;

@MongoEntity(collection = "profiles")
public class Profile extends PanacheMongoEntity {

    private final String username;

    private final List<Exercice> exercices = new ArrayList<>();

    public Profile(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public List<Exercice> getExercices() {
        return exercices;
    }
}
