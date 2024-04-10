import org.arobase.infrastructure.persistence.repository.DifficultyRepository;
import org.arobase.infrastructure.persistence.service.DifficultyService;
import org.jboss.logging.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class DifficultyServiceTest {

    @Mock
    private DifficultyRepository difficultyRepository;

    @Mock
    private Logger logger;

    @InjectMocks
    private DifficultyService difficultyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllDifficulties_ReturnsAllDifficulties() {
        // Act
        difficultyService.findAllDifficulties();

        // Assert
        verify(difficultyRepository, times(1)).listAll();
    }
}
