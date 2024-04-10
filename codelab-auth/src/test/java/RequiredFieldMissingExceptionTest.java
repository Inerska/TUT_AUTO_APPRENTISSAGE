import io.vertx.core.json.JsonObject;
import jakarta.ws.rs.core.Response;
import org.arobase.infrastructure.exception.RequiredFieldMissingException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RequiredFieldMissingExceptionTest {

    @Test
    public void testConstructor() {
        Response.Status status = Response.Status.BAD_REQUEST;
        String message = "Required field is missing";

        RequiredFieldMissingException exception = new RequiredFieldMissingException(status, message);

        assertEquals(status.getStatusCode(), exception.getResponse().getStatus());
        JsonObject entity = (JsonObject) exception.getResponse().getEntity();
        assertEquals(status.getStatusCode(), entity.getInteger("code"));
        assertEquals(message, entity.getString("message"));
    }
}
