import org.arobase.domain.model.request.ExerciceCreateRequest;
import org.arobase.domain.model.request.MessagingRequest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class ExerciceCreateRequestTest {

    @Test
    public void testConstructor() {
        ExerciceCreateRequest request = new ExerciceCreateRequest(
                "Pellizzari Theo",
                "test code",
                "Java",
                Collections.emptyList(),
                "banner",
                "author",
                "testCode",
                null,
                null,
                0,
                LocalDateTime.now()
        );

        assertEquals("author", request.getAuthor());
        assertEquals("testCode", request.getTestCode());
        assertEquals(null, request.getLanguage());
    }

    @Test
    public void testGettersAndSetters() {
        ExerciceCreateRequest request = new ExerciceCreateRequest(
                "Pellizzari Theo",
                "test code",
                "Java",
                Collections.emptyList(),
                "banner",
                "author",
                "testCode",
                null,
                null,
                0,
                LocalDateTime.now()
        );

        assertEquals("author", request.getAuthor());
        assertEquals("testCode", request.getTestCode());

        request.setAuthor("Gridel Alexis");
        request.setTestCode("new test code");


        assertEquals("Gridel Alexis", request.getAuthor());
        assertEquals("new test code", request.getTestCode());
    }

    @Test
    public void testMessagingRequestInterface() {
        assertSame(ExerciceCreateRequest.class.getInterfaces()[0], MessagingRequest.class);
    }
}
