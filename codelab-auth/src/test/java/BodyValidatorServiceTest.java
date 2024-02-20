import org.arobase.infrastructure.exception.RequiredFieldMissingException;
import org.junit.jupiter.api.Test;
import org.arobase.infrastructure.service.BodyValidatorService;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BodyValidatorServiceTest {

    // Classe de test pour BodyValidatorService

    // Classe interne utilisée pour simuler un DTO avec deux champs
    static class TestDTO {
        private String field1;
        private String field2;

        // Constructeur prenant deux champs en entrée
        public TestDTO(String field1, String field2) {
            this.field1 = field1;
            this.field2 = field2;
        }

        // Getters pour les champs du DTO
        public String getField1() {
            return field1;
        }

        public String getField2() {
            return field2;
        }
    }

    // Test pour vérifier si tous les champs du DTO sont présents
    @Test
    void testValidateBody_AllFieldsPresent() {
        // Préparation des données
        TestDTO testDTO = new TestDTO("value1", "value2");
        BodyValidatorService bodyValidatorService = new BodyValidatorService();

        // Aucune exception ne devrait être levée
        bodyValidatorService.validateBody(testDTO);
    }

    // Test pour vérifier si le champ 1 du DTO est manquant
    @Test
    void testValidateBody_Field1Missing() {
        // Préparation des données
        TestDTO testDTO = new TestDTO(null, "value2");
        BodyValidatorService bodyValidatorService = new BodyValidatorService();

        // Une RequiredFieldMissingException doit être levée
        assertThrows(RequiredFieldMissingException.class, () -> bodyValidatorService.validateBody(testDTO));
    }

    // Test pour vérifier si le champ 2 du DTO est manquant
    @Test
    void testValidateBody_Field2Missing() {
        // Préparation des données
        TestDTO testDTO = new TestDTO("value1", null);
        BodyValidatorService bodyValidatorService = new BodyValidatorService();

        // Une RequiredFieldMissingException doit être levée
        assertThrows(RequiredFieldMissingException.class, () -> bodyValidatorService.validateBody(testDTO));
    }
}
