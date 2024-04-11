import io.vertx.core.json.JsonObject;
import jakarta.ws.rs.core.Response;
import org.arobase.infrastructure.exception.AuthenticationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthenticationExceptionTest {

    @Test
    public void testConstructor() {
        Response.Status status = Response.Status.UNAUTHORIZED;
        String message = "Unauthorized access";

        AuthenticationException exception = new AuthenticationException(status, message);

        assertEquals(status.getStatusCode(), exception.getResponse().getStatus());
        JsonObject entity = (JsonObject) exception.getResponse().getEntity();
        assertEquals(status.getStatusCode(), entity.getInteger("code"));
        assertEquals(message, entity.getString("message"));
    }
}
