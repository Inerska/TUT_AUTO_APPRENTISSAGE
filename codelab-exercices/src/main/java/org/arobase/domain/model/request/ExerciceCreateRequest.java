package org.arobase.domain.model.request;

public final class ExerciceCreateRequest implements MessagingRequest {

    private final String title;
    private final String wording;
    private final String author;
    private final String testCode;
    private final String language;

    public ExerciceCreateRequest(String title, String wording, String author, String testCode, String language) {
        this.title = title;
        this.wording = wording;
        this.author = author;
        this.testCode = testCode;
        this.language = language;
    }

    public String getTitle() {
        return title;
    }

    public String getWording() {
        return wording;
    }

    public String getLanguage() {
        return language;
    }

    public String getAuthor() {
        return author;
    }


    public String getTestCode() {
        return testCode;
    }
}
