package org.arobase.infrastructure.persistence.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;

/**
 * The account entity.
 */
@Entity
public class Account extends PanacheEntity {

    /**
     * The id of the account.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * The username of the account.
     */
    private String username;

    /**
     * The password of the account.
     */
    private String password;

    /**
     * The name of the account.
     */
    private String name;

    /**
     * The surname of the account.
     */
    private String surname;

    /**
     * The email of the account.
     */
    private String email;

    /**
     * The role of the account.
     */
    private RoleType role;

    /**
     * The finished lessons of the account.
     */
    private List<Lesson> finishedLessons;

    /**
     * The started lessons of the account.
     */
    private List<StartedLesson> startedLessons;

}
