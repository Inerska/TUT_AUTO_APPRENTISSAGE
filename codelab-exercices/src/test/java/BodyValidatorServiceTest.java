import org.arobase.infrastructure.exception.RequiredFieldMissingException;
import org.arobase.infrastructure.persistence.service.BodyValidatorService;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BodyValidatorServiceTest {

    @Test
    void validateBody_WithNullField_ThrowsRequiredFieldMissingException() {
        BodyValidatorService bodyValidatorService = new BodyValidatorService();
        TestDTO testDTO = new TestDTO(null, "value");

        assertThrows(RequiredFieldMissingException.class, () -> bodyValidatorService.validateBody(testDTO));
    }

    @Test
    void validateBody_WithNonNullField_NoExceptionThrown() {
        BodyValidatorService bodyValidatorService = new BodyValidatorService();
        TestDTO testDTO = new TestDTO("value", "value");

        assertDoesNotThrow(() -> bodyValidatorService.validateBody(testDTO));
    }

    private static class TestDTO {
        private String nullableField;
        private String nonNullableField;

        public TestDTO(String nullableField, String nonNullableField) {
            this.nullableField = nullableField;
            this.nonNullableField = nonNullableField;
        }
    }
}
