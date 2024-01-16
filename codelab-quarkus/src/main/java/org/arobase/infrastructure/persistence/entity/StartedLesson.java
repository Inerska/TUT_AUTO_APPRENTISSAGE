package org.arobase.infrastructure.persistence.entity;

import jakarta.persistence.Entity;

/**
 * The started lesson entity.
 */
@Entity
public class StartedLesson extends Lesson {

    /**
     * The last exercice of the started lesson.
     */
    private Exercice lastExercice;
}
