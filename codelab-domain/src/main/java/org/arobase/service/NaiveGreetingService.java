package org.arobase.service;

import jakarta.enterprise.context.ApplicationScoped;

/**
 * Describes a service that greets people.
 */
@ApplicationScoped
public final class NaiveGreetingService implements GreetingService {

    /**
     * Greets the given name.
     *
     * @param name the name to greet
     * @return the greeting
     */
    @Override
    public String greet(String name) {
        return "Hello " + name + "!";
    }
}
