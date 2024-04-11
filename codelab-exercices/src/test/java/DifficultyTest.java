import org.arobase.infrastructure.persistence.entity.Difficulty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DifficultyTest {

    @Test
    public void testDifficultyName() {
        Difficulty difficulty = new Difficulty();
        String expectedName = "Hard";

        difficulty.name = expectedName;

        Assertions.assertEquals(expectedName, difficulty.name);
    }

    @Test
    public void testDifficultyId() {
        Difficulty difficulty = new Difficulty();
        String validId = "5e2e4dfb69be0c0071e4bfe9";

        difficulty.setId(validId);

        Assertions.assertEquals(validId, difficulty.id.toHexString());
    }

    @Test
    public void testDifficultyNameGetter() {
        Difficulty difficulty = new Difficulty();
        String expectedName = "Easy";
        difficulty.name = expectedName;

        String actualName = difficulty.getName();

        Assertions.assertEquals(expectedName, actualName);
    }
}
