package org.arobase.service;

/**
 * Describes a service that greets people.
 */
public interface GreetingService {

    /**
     * Greets the given name.
     * @param name the name to greet
     * @return the greeting
     */
    String greet(final String name);
}
