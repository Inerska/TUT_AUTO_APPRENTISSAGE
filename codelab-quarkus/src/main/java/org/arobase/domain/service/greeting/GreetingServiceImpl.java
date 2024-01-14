package org.arobase.domain.service.greeting;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GreetingServiceImpl implements GreetingService{

    /**
     * Greet someone.
     *
     * @param name the name of the person to greet
     * @return the greeting
     */
    @Override
    public String greet(String name) {
        return "Hello " + name + " from Alexis !";
    }
}
