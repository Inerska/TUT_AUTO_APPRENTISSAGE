import org.arobase.infrastructure.persistence.entity.Exercice;
import org.arobase.infrastructure.persistence.entity.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ExerciceTest {

    @Test
    public void testExerciceTitle() {
        Exercice exercice = new Exercice();
        String expectedTitle = "Test Exercice";

        exercice.title = expectedTitle;

        Assertions.assertEquals(expectedTitle, exercice.title);
    }

    @Test
    public void testExerciceCreatedAt() {
        Exercice exercice = new Exercice();
        LocalDateTime expectedCreatedAt = LocalDateTime.now();

        exercice.createdAt = expectedCreatedAt;

        Assertions.assertEquals(expectedCreatedAt, exercice.createdAt);
    }

    @Test
    public void testExerciceTasks() {
        Exercice exercice = new Exercice();
        Task task1 = new Task();
        Task task2 = new Task();
        List<Task> expectedTasks = new ArrayList<>();
        expectedTasks.add(task1);
        expectedTasks.add(task2);

        exercice.tasks = expectedTasks;

        Assertions.assertEquals(expectedTasks, exercice.tasks);
    }
}
