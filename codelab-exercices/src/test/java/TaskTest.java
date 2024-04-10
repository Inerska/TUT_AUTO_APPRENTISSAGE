import org.arobase.infrastructure.persistence.entity.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    public void testTaskContent() {
        Task task = new Task();
        String expectedContent = "Task content";

        task.content = expectedContent;

        Assertions.assertEquals(expectedContent, task.content);
    }

    @Test
    public void testTaskOrder() {
        Task task = new Task();
        int expectedOrder = 1;

        task.order = expectedOrder;

        Assertions.assertEquals(expectedOrder, task.order);
    }
}
