package org.arobase.infrastructure.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

/**
 * The started lesson entity.
 */
@Entity
public class StartedLesson extends Lesson {

    /**
     * The last exercice of the started lesson.
     */
    @OneToOne
    private Exercice lastExercice;
}
