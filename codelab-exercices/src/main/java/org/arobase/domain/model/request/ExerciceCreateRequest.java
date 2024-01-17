package org.arobase.domain.model.request;

public final class ExerciceCreateRequest implements MessagingRequest {
    private String author;
    private String testCode;

    private String language;

    public ExerciceCreateRequest(String author, String testCode, String language) {
        this.author = author;
        this.testCode = testCode;
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTestCode() {
        return testCode;
    }

    public void setTestCode(String testCode) {
        this.testCode = testCode;
    }
}
