import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.arobase.domain.model.request.MessagingRequest;
import org.arobase.domain.model.request.ExerciceSubmitRequest;

public class ExerciceSubmitRequestTest {

    @Test
    public void testConstructorAndGetters() {
        // Création d'une instance de ExerciceSubmitRequest
        ExerciceSubmitRequest request = new ExerciceSubmitRequest("test code", "Java", "exerciceId", "resultObjectId");

        // Vérification des valeurs initialisées par le constructeur
        assertEquals("test code", request.getCode());
        assertEquals("Java", request.getLanguage());
        assertEquals("exerciceId", request.getExerciceId());
        assertEquals("resultObjectId", request.getExerciceResultObjectId());
    }

    @Test
    public void testSetterAndGetters() {
        // Création d'une instance de ExerciceSubmitRequest
        ExerciceSubmitRequest request = new ExerciceSubmitRequest("test code", "Java", "exerciceId", "resultObjectId");

        // Modification des valeurs avec les setters
        request.setExerciceResultObjectId("newResultObjectId");

        // Vérification des valeurs après modification
        assertEquals("newResultObjectId", request.getExerciceResultObjectId());
    }

    @Test
    public void testToString() {
        // Création d'une instance de ExerciceSubmitRequest
        ExerciceSubmitRequest request = new ExerciceSubmitRequest("test code", "Java", "exerciceId", "resultObjectId");

        // Vérification de la méthode toString
        assertEquals("ExerciceRequest{code='test code', language='Java', id='exerciceId'}", request.toString());
    }

    @Test
    public void testMessagingRequestInterface() {
        // Vérification que la classe implémente l'interface MessagingRequest
        assertTrue(ExerciceSubmitRequest.class.getInterfaces()[0] == MessagingRequest.class);
    }
}
