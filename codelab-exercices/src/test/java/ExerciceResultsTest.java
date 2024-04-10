import org.arobase.infrastructure.persistence.entity.ExerciceResults;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class ExerciceResultsTest {

    @Test
    public void testExerciceResultsStatus() {
        ExerciceResults exerciceResults = new ExerciceResults();
        String expectedStatus = "SUCCESS";

        exerciceResults.status = expectedStatus;

        Assertions.assertEquals(expectedStatus, exerciceResults.status);
    }

    @Test
    public void testExerciceResultsTimestamp() {
        ExerciceResults exerciceResults = new ExerciceResults();
        LocalDateTime expectedTimestamp = LocalDateTime.now();

        Assertions.assertNotNull(exerciceResults.timestamp);
        Assertions.assertTrue(expectedTimestamp.isBefore(exerciceResults.timestamp.plusSeconds(1))
                || expectedTimestamp.isEqual(exerciceResults.timestamp)
                || expectedTimestamp.isAfter(exerciceResults.timestamp.minusSeconds(1)));
    }

}
