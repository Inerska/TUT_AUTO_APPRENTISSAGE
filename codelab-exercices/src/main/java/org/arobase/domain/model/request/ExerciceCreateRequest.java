package org.arobase.domain.model.request;

import org.arobase.infrastructure.persistence.entity.Difficulty;
import org.arobase.infrastructure.persistence.entity.Language;
import org.arobase.infrastructure.persistence.entity.Task;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public final class ExerciceCreateRequest implements MessagingRequest {

    private final String title;

    private final String description;

    private final String instructions;

    private final List<Task> tasks;

    private final String banner;

    private final String author;

    private final String testCode;

    public final Language language;

    public final Difficulty difficulty;

    public final int nbTests;

    public final LocalDateTime createdAt;

    public ExerciceCreateRequest(String title, String description, String instructions, List<Task> tasks, String banner, String author, String testCode, Language language, Difficulty difficulty, int nbTests, LocalDateTime createdAt) {
        this.title = title;
        this.description = description;
        this.instructions = instructions;
        this.tasks = tasks;
        this.banner = banner;
        this.author = author;
        this.testCode = testCode;
        this.language = language;
        this.difficulty = difficulty;
        this.nbTests = nbTests;
        this.createdAt = createdAt;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getInstructions() {
        return instructions;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public String getBanner() {
        return banner;
    }

    public String getAuthor() {
        return author;
    }

    public String getTestCode() {
        return testCode;
    }

    public Language getLanguage() {
        return language;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public int getNbTests() {
        return nbTests;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

}
