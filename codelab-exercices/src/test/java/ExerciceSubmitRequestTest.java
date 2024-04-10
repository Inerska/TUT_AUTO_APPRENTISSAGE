import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.arobase.domain.model.request.MessagingRequest;
import org.arobase.domain.model.request.ExerciceSubmitRequest;

/**
 * Classe de test pour la classe ExerciceSubmitRequest.
 */
public class ExerciceSubmitRequestTest {

    /**
     * Teste le constructeur et les getters de la classe ExerciceSubmitRequest.
     */
    @Test
    public void testConstructorAndGetters() {
        ExerciceSubmitRequest request = new ExerciceSubmitRequest("test code", "Java", "exerciceId", "resultObjectId");

        assertEquals("test code", request.getCode());
        assertEquals("Java", request.getLanguage());
        assertEquals("exerciceId", request.getExerciceId());
        assertEquals("resultObjectId", request.getExerciceResultObjectId());
    }

    /**
     * Teste les setters et les getters de la classe ExerciceSubmitRequest.
     */
    @Test
    public void testSetterAndGetters() {
        ExerciceSubmitRequest request = new ExerciceSubmitRequest("test code", "Java", "exerciceId", "resultObjectId");

        request.setExerciceResultObjectId("newResultObjectId");

        assertEquals("newResultObjectId", request.getExerciceResultObjectId());
    }

    /**
     * Teste la méthode toString de la classe ExerciceSubmitRequest.
     */
    @Test
    public void testToString() {
        ExerciceSubmitRequest request = new ExerciceSubmitRequest("test code", "Java", "exerciceId", "resultObjectId");

        assertEquals("ExerciceRequest{code='test code', language='Java', id='exerciceId'}", request.toString());
    }

    /**
     * Teste que la classe ExerciceSubmitRequest implémente l'interface MessagingRequest.
     */
    @Test
    public void testMessagingRequestInterface() {
        assertSame(ExerciceSubmitRequest.class.getInterfaces()[0], MessagingRequest.class);
    }
}