import org.arobase.infrastructure.exception.RequiredFieldMissingException;
import org.junit.jupiter.api.Test;
import org.arobase.infrastructure.service.BodyValidatorService;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BodyValidatorServiceTest {

    static class TestDTO {
        private String field1;
        private String field2;

        public TestDTO(String field1, String field2) {
            this.field1 = field1;
            this.field2 = field2;
        }

        public String getField1() {
            return field1;
        }

        public String getField2() {
            return field2;
        }
    }

    @Test
    void testValidateBody_AllFieldsPresent() {
        TestDTO testDTO = new TestDTO("value1", "value2");
        BodyValidatorService bodyValidatorService = new BodyValidatorService();

        bodyValidatorService.validateBody(testDTO);
    }

    @Test
    void testValidateBody_Field1Missing() {
        TestDTO testDTO = new TestDTO(null, "value2");
        BodyValidatorService bodyValidatorService = new BodyValidatorService();

        assertThrows(RequiredFieldMissingException.class, () -> bodyValidatorService.validateBody(testDTO));
    }

    @Test
    void testValidateBody_Field2Missing() {
        TestDTO testDTO = new TestDTO("value1", null);
        BodyValidatorService bodyValidatorService = new BodyValidatorService();

        assertThrows(RequiredFieldMissingException.class, () -> bodyValidatorService.validateBody(testDTO));
    }
}
