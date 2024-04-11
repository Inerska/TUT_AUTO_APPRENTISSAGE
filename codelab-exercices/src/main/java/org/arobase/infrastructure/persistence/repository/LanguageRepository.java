package org.arobase.infrastructure.persistence.repository;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import org.arobase.infrastructure.persistence.entity.Language;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.Optional;
import java.util.regex.Pattern;

@ApplicationScoped
public class LanguageRepository implements PanacheMongoRepository<Language> {

    public Optional<Language> findByIdOptional(String id) {
        if (id == null || id.length() != 24 || !Pattern.matches("[0-9a-fA-F]{24}", id)) {
            // Si l'ID est null, a une longueur incorrecte ou contient des caractères non hexadécimaux, retourner un Optional vide
            return Optional.empty();
        }
        return find("_id", id).firstResultOptional();
    }
}
