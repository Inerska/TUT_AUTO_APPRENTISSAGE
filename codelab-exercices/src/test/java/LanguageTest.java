import org.arobase.infrastructure.persistence.entity.Language;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LanguageTest {

    @Test
    public void testLanguageName() {
        Language language = new Language();
        String expectedName = "English";

        language.name = expectedName;

        Assertions.assertEquals(expectedName, language.name);
    }

    @Test
    public void testLanguageAbbreviation() {
        Language language = new Language();
        String expectedAbbreviation = "EN";

        language.abbreviation = expectedAbbreviation;

        Assertions.assertEquals(expectedAbbreviation, language.abbreviation);
    }
}
