import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.arobase.domain.model.request.MessagingRequest;
import org.arobase.domain.model.request.ExerciceCreateRequest;

/**
 * Classe de test pour la classe ExerciceCreateRequest.
 */
public class ExerciceCreateRequestTest {

    /**
     * Teste le constructeur de la classe ExerciceCreateRequest.
     */
    @Test
    public void testConstructor() {
        // Vérification du constructeur
        ExerciceCreateRequest request = new ExerciceCreateRequest("Pellizzari Theo", "test code", "Java");

        assertEquals("Pellizzari Theo", request.getAuthor());
        assertEquals("test code", request.getTestCode());
        assertEquals("Java", request.getLanguage());
    }

    /**
     * Teste les getters et setters de la classe ExerciceCreateRequest.
     */
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

    /**
     * Teste l'implémentation de l'interface MessagingRequest par la classe ExerciceCreateRequest.
     */
    @Test
    public void testMessagingRequestInterface() {
        // Vérification que la classe implémente l'interface MessagingRequest
        assertSame(ExerciceCreateRequest.class.getInterfaces()[0], MessagingRequest.class);
    }

}
