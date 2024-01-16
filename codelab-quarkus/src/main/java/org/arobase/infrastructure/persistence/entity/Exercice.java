package org.arobase.infrastructure.persistence.entity;

import jakarta.persistence.*;

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
    private long id;

    /**
     * The tilted of the exercice.
     */
    private String tilted;

    /**
     * The path of the correction file
     */
    private String correctionFilePath;
}
