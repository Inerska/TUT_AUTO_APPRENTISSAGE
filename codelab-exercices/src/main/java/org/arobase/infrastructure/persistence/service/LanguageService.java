package org.arobase.infrastructure.persistence.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.arobase.domain.model.request.LanguageCreateRequest;
import org.arobase.infrastructure.persistence.entity.Language;
import org.arobase.infrastructure.persistence.repository.LanguageRepository;
import org.bson.types.ObjectId;
import org.jboss.logging.Logger;

@ApplicationScoped
public class LanguageService {

    private final LanguageRepository languageRepository;

    private final Logger logger;

    public LanguageService(LanguageRepository languageRepository, Logger logger) {
        this.languageRepository = languageRepository;
        this.logger = logger;
    }

    /**
     * Create a new language.
     *
     * @param language the language to create
     * @return the created language
     */
    public Language createLanguage(LanguageCreateRequest language) {
        final var lg = new Language();
        lg.name = language.name();
        lg.abbreviation = language.abbreviation();

        lg.persist();
        return lg;
    }

    /**
     * Update a language.
     *
     * @param language the language to update
     * @return the updated language
     */
    public Language updateLanguage(String id, LanguageCreateRequest language) {
        final var lg = languageRepository.findByIdOptional(new ObjectId(id))
                .orElseThrow(() -> new RuntimeException("Language not found"));
        lg.name = language.name();

        lg.persistOrUpdate();
        return lg;
    }

    /**
     * Delete a language.
     *
     * @param id the language id
     */
    public void deleteLanguage(String id) {
        final var lg = languageRepository.findByIdOptional(new ObjectId(id))
                .orElseThrow(() -> new RuntimeException("Language not found"));
        lg.delete();
    }

    /**
     * Find a language by id.
     *
     * @param id the language id
     * @return the found language
     */
    public Language findLanguageById(String id) {
        return languageRepository.findByIdOptional(new ObjectId(id))
                .orElseThrow(() -> new RuntimeException("Language not found"));
    }

    /**
     * Find all languages.
     *
     * @return the list of languages
     */
    public Iterable<Language> findAllLanguages() {
        return languageRepository.listAll();
    }

}
