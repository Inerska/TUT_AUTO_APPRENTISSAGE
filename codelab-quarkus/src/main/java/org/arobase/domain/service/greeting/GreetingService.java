package org.arobase.domain.service.greeting;

/**
 * Service to greet people.
 */
public interface GreetingService {

    /**
     * Greet someone.
     *
     * @param name the name of the person to greet
     * @return the greeting
     */
    String greet(String name);
}
