package org.arobase.infrastructure.persistence.service;

import jakarta.inject.Singleton;
import jakarta.ws.rs.core.Response;
import org.arobase.infrastructure.exception.RequiredFieldMissingException;

import java.lang.reflect.Field;

/**
 * The body validator service.
 */
@Singleton
public class BodyValidatorService {

    /**
     * The validate body.
     * @param DTO
     */
    public void validateBody(Object DTO) {
        for (Field field : DTO.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                if (field.get(DTO) == null) {
                    throw new RequiredFieldMissingException(Response.Status.BAD_REQUEST, "Le champ '" + field.getName() + "' est requis.");
                }
            }catch (IllegalAccessException e){
                e.fillInStackTrace();
            }
        }
    }
}
