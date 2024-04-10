package org.arobase.infrastructure.persistence.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import org.arobase.domain.exception.ProfileException;
import org.arobase.infrastructure.persistence.entity.Profile;
import org.arobase.infrastructure.persistence.repository.ProfileRepository;
import org.bson.types.ObjectId;

@ApplicationScoped
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileService(final ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public Profile createProfile(final String username){
        if(profileRepository.find("username", username).count() > 0){
            throw new ProfileException(Response.Status.CONFLICT, "Username already exists");
        }
        Profile profile = new Profile();
        profile.username = username;
        profileRepository.persist(profile);
        return profile;
    }

    public Profile getProfile(final String id){
        return profileRepository.findByIdOptional(new ObjectId(id))
                .orElseThrow(() ->
                        new ProfileException(Response.Status.NOT_FOUND, "Profile not found"));
    }
}
