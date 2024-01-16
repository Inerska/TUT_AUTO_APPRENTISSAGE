package org.arobase.infrastructure.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * The exercice entity.
 */
@Entity
public class Exercice {

    /**
     * The id of the exercice.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * The name of the exercice.
     */
    private Lesson lesson;

    /**
     * The name of the exercice.
     */
    private String tilted;

    /**
     * The name of the exercice.
     */
    private String correctionFilePath;
}
