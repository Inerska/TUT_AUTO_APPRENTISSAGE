import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.arobase.domain.model.request.MessagingRequest;
import org.arobase.domain.model.request.ExerciceCreateRequest;

public class ExerciceCreateRequestTest {

    @Test
    public void testConstructor() {
        // Vérification du constructeur
        ExerciceCreateRequest request = new ExerciceCreateRequest("Pellizzari Theo", "test code", "Java");

        assertEquals("Pellizzari Theo", request.getAuthor());
        assertEquals("test code", request.getTestCode());
        assertEquals("Java", request.getLanguage());
    }

    @Test
    public void testGettersAndSetters() {
        // Création d'une instance de ExerciceCreateRequest
        ExerciceCreateRequest request = new ExerciceCreateRequest("Pellizzari Theo", "test code", "Java");

        // Vérification des getters
        assertEquals("Pellizzari Theo", request.getAuthor());
        assertEquals("test code", request.getTestCode());
        assertEquals("Java", request.getLanguage());

        // Modification des valeurs et vérification des setters
        request.setAuthor("Gridel Alexis");
        request.setTestCode("new test code");
        request.setLanguage("Python");

        assertEquals("Gridel Alexis", request.getAuthor());
        assertEquals("new test code", request.getTestCode());
        assertEquals("Python", request.getLanguage());
    }

    @Test
    public void testMessagingRequestInterface() {
        // Vérification que la classe implémente l'interface MessagingRequest
        assertTrue(ExerciceCreateRequest.class.getInterfaces()[0] == MessagingRequest.class);
    }

}
