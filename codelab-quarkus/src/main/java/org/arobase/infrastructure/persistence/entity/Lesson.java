package org.arobase.infrastructure.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;

/**
 * The lesson entity.
 */
@Entity
public class Lesson {

    /**
     * The id of the lesson.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * The name of the lesson.
     */
    private String name;

    /**
     * The description of the lesson.
     */
    private String description;

    /**
     * The difficulty of the lesson.
     */
    private int difficulty;

    /**
     * The exercices of the lesson.
     */
    private List<Exercice> exercices;
}
