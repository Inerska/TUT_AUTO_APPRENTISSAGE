package org.arobase.infrastructure.persistence.repository;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.arobase.infrastructure.persistence.entity.Profile;
import org.jboss.resteasy.reactive.RestQuery;

@ApplicationScoped
public class ProfileRepository implements PanacheMongoRepository<Profile> {



}
